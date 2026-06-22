package com.priyanshu.floralens.data

import java.util.UUID

data class ClassificationResult(
    val diseaseName: String,
    val confidence: Float,
    val isPlantDetected: Boolean
)

data class ScanResult(
    val id: Int = 0,
    val diseaseName: String,
    val cause: String,
    val treatment: String,
    val timestamp: Long,
    val confidence: Float,
    val imagePath: String = ""
)

data class PlantProfile(
    val plantId: String = UUID.randomUUID().toString(),
    val customName: String,
    val scans: List<ScanResult> = emptyList()
) {
    val latestScan: ScanResult? get() = scans.maxByOrNull { it.timestamp }
    val latestTimestamp: Long get() = latestScan?.timestamp ?: 0L
}
