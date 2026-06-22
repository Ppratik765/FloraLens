package com.priyanshu.floralens.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.floralens.classifier.TFLiteImageClassifier
import com.priyanshu.floralens.data.ClassificationResult
import com.priyanshu.floralens.data.DiseaseDatabase
import com.priyanshu.floralens.data.PlantProfile
import com.priyanshu.floralens.data.ScanResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

sealed interface AppState {
    object Idle : AppState
    object Analyzing : AppState
    data class AwaitingPlantSelection(
        val classification: ClassificationResult,
        val scanResult: ScanResult,
        val bitmap: Bitmap
    ) : AppState
    data class Result(val classification: ClassificationResult, val scanResult: ScanResult?) : AppState
    data class Error(val message: String) : AppState
}

class MainViewModel : ViewModel() {

    private val _appState = MutableStateFlow<AppState>(AppState.Idle)
    val appState: StateFlow<AppState> = _appState.asStateFlow()

    private val _plantProfiles = MutableStateFlow<List<PlantProfile>>(emptyList())
    val plantProfiles: StateFlow<List<PlantProfile>> = _plantProfiles.asStateFlow()

    private val _isLightMode = MutableStateFlow(false)
    val isLightMode: StateFlow<Boolean> = _isLightMode.asStateFlow()

    fun toggleTheme() {
        _isLightMode.value = !_isLightMode.value
    }

    // Legacy compatibility: flat list derived from profiles
    val scanHistory: StateFlow<List<ScanResult>>
        get() = MutableStateFlow(
            _plantProfiles.value.flatMap { it.scans }.sortedByDescending { it.timestamp }
        )

    private var classifier: TFLiteImageClassifier? = null

    // Used when updating an existing plant's condition from the detail screen
    private var _pendingPlantId: String? = null
    val pendingPlantId: String? get() = _pendingPlantId

    fun setPendingPlantId(plantId: String?) {
        _pendingPlantId = plantId
    }

    fun setClassifier(classifier: TFLiteImageClassifier) {
        this.classifier = classifier
    }

    private fun cropToSquare(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val size = minOf(width, height)
        val x = (width - size) / 2
        val y = (height - size) / 2
        return Bitmap.createBitmap(bitmap, x, y, size, size)
    }

    fun analyzeImage(bitmap: Bitmap) {
        if (_appState.value is AppState.Analyzing) return
        val currentClassifier = classifier ?: return

        viewModelScope.launch(Dispatchers.Default) {
            _appState.value = AppState.Analyzing
            try {
                val croppedBitmap = cropToSquare(bitmap)
                val classification = currentClassifier.classify(croppedBitmap)

                if (classification.isPlantDetected) {
                    val info = DiseaseDatabase.getInfo(classification.diseaseName)
                    val scanResult = ScanResult(
                        id = _plantProfiles.value.flatMap { it.scans }.size + 1,
                        diseaseName = info.displayName,
                        cause = info.cause,
                        treatment = info.treatment,
                        timestamp = System.currentTimeMillis(),
                        confidence = classification.confidence,
                        imagePath = "" // Will be set when user confirms
                    )

                    // Check if we have a pending plant ID (from Update Condition flow)
                    val pendingId = _pendingPlantId
                    if (pendingId != null) {
                        // Directly show result — will be saved via addToExistingPlant externally
                        _appState.value = AppState.AwaitingPlantSelection(
                            classification = classification,
                            scanResult = scanResult,
                            bitmap = croppedBitmap
                        )
                    } else {
                        // Normal scan — ask user to select new or existing plant
                        _appState.value = AppState.AwaitingPlantSelection(
                            classification = classification,
                            scanResult = scanResult,
                            bitmap = croppedBitmap
                        )
                    }
                } else {
                    _appState.value = AppState.Result(classification, null)
                }
            } catch (e: Exception) {
                _appState.value = AppState.Error(e.message ?: "An error occurred during analysis")
            }
        }
    }

    fun createNewPlant(context: Context, customName: String) {
        val state = _appState.value
        if (state !is AppState.AwaitingPlantSelection) return

        viewModelScope.launch(Dispatchers.IO) {
            val imagePath = saveBitmapToInternal(context, state.bitmap)
            val updatedScan = state.scanResult.copy(imagePath = imagePath)
            val newProfile = PlantProfile(
                customName = customName,
                scans = listOf(updatedScan)
            )
            _plantProfiles.value = _plantProfiles.value + newProfile
            _pendingPlantId = null
            _appState.value = AppState.Result(state.classification, updatedScan)
        }
    }

    fun addToExistingPlant(context: Context, plantId: String) {
        val state = _appState.value
        if (state !is AppState.AwaitingPlantSelection) return

        viewModelScope.launch(Dispatchers.IO) {
            val imagePath = saveBitmapToInternal(context, state.bitmap)
            val updatedScan = state.scanResult.copy(imagePath = imagePath)
            _plantProfiles.value = _plantProfiles.value.map { profile ->
                if (profile.plantId == plantId) {
                    profile.copy(scans = profile.scans + updatedScan)
                } else profile
            }
            _pendingPlantId = null
            _appState.value = AppState.Result(state.classification, updatedScan)
        }
    }

    fun getPlantById(plantId: String): PlantProfile? {
        return _plantProfiles.value.find { it.plantId == plantId }
    }

    fun dismissResult() {
        _pendingPlantId = null
        _appState.value = AppState.Idle
    }

    private fun saveBitmapToInternal(context: Context, bitmap: Bitmap): String {
        val dir = File(context.filesDir, "plant_images")
        if (!dir.exists()) dir.mkdirs()
        val file = File(dir, "scan_${UUID.randomUUID()}.jpg")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
        return file.absolutePath
    }

    override fun onCleared() {
        super.onCleared()
        classifier?.close()
    }
}
