package com.priyanshu.floralens.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.floralens.classifier.TFLiteImageClassifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AppState {
    object Idle : AppState
    object Analyzing : AppState
    data class Result(val disease: String) : AppState
    data class Error(val message: String) : AppState
}

class MainViewModel : ViewModel() {

    private val _appState = MutableStateFlow<AppState>(AppState.Idle)
    val appState: StateFlow<AppState> = _appState.asStateFlow()

    private var classifier: TFLiteImageClassifier? = null

    fun setClassifier(classifier: TFLiteImageClassifier) {
        this.classifier = classifier
    }

    fun analyzeImage(bitmap: Bitmap) {
        if (_appState.value is AppState.Analyzing) return
        val currentClassifier = classifier ?: return

        viewModelScope.launch(Dispatchers.Default) {
            _appState.value = AppState.Analyzing
            try {
                val result = currentClassifier.classify(bitmap)
                _appState.value = AppState.Result(result)
            } catch (e: Exception) {
                _appState.value = AppState.Error(e.message ?: "An error occurred during analysis")
            }
        }
    }

    fun resetState() {
        _appState.value = AppState.Idle
    }

    override fun onCleared() {
        super.onCleared()
        classifier?.close()
    }
}
