package com.priyanshu.floralens.ui.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import android.view.SoundEffectConstants
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.priyanshu.floralens.data.PlantProfile
import com.priyanshu.floralens.ui.components.BotanicalBackgroundDecorations
import com.priyanshu.floralens.ui.theme.*
import com.priyanshu.floralens.viewmodel.MainViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryTimelineScreen(viewModel: MainViewModel, onPlantClick: (String) -> Unit) {
    val profiles by viewModel.plantProfiles.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BotanicalBackgroundDecorations()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.History,
                    contentDescription = null,
                    tint = FloraVibrant,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Plant Timeline",
                    style = MaterialTheme.typography.headlineLarge,
                    color = FloraVibrant,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (profiles.isEmpty()) "" else "${profiles.size} plants tracked",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (profiles.isEmpty()) {
                EmptyTimelineState()
            } else {
                val sortedProfiles = profiles.sortedByDescending { it.latestTimestamp }
                val chunkedProfiles = sortedProfiles.chunked(2)
                val timelineColor = FloraVibrant
                val strokeWidth = 4.dp

                androidx.compose.foundation.lazy.LazyColumn(
                    contentPadding = PaddingValues(top = 24.dp, bottom = 120.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(chunkedProfiles) { index, rowProfiles ->
                        val isEvenRow = index % 2 == 0
                        val hasNextRow = index < chunkedProfiles.lastIndex
                        val isLastRow = index == chunkedProfiles.lastIndex
                        val numNodesInRow = rowProfiles.size
                        
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .drawBehind {
                                    val cx1 = size.width / 4
                                    val cx2 = size.width * 3 / 4
                                    val dotY = 127.dp.toPx()
                                    val sw = strokeWidth.toPx()
                                    val color = timelineColor.copy(alpha = 0.5f)
                                    
                                    // Entry tail
                                    if (index == 0) {
                                        val startX = cx1 - 60.dp.toPx()
                                        drawLine(color, Offset(startX, dotY), Offset(cx1, dotY), sw)
                                    }

                                    // Horizontal segment
                                    if (numNodesInRow == 2) {
                                        drawLine(color, Offset(cx1, dotY), Offset(cx2, dotY), sw)
                                    } else if (numNodesInRow == 1 && isLastRow && index != 0) {
                                        val startX = if (isEvenRow) cx1 else cx2
                                        val endX = if (isEvenRow) cx1 + 60.dp.toPx() else cx2 - 60.dp.toPx()
                                        drawLine(color, Offset(startX, dotY), Offset(endX, dotY), sw)
                                        drawArrow(endX, dotY, isEvenRow, color, sw)
                                    }

                                    // Trailing extension for 2 nodes
                                    if (numNodesInRow == 2 && isLastRow) {
                                        val startX = if (isEvenRow) cx2 else cx1
                                        val endX = if (isEvenRow) cx2 + 60.dp.toPx() else cx1 - 60.dp.toPx()
                                        drawLine(color, Offset(startX, dotY), Offset(endX, dotY), sw)
                                        drawArrow(endX, dotY, isEvenRow, color, sw)
                                    }

                                    // C-curve to next row
                                    if (hasNextRow) {
                                        val startX = if (isEvenRow) cx2 else cx1
                                        val endY = dotY + size.height
                                        val controlX = if (isEvenRow) size.width - 16.dp.toPx() else 16.dp.toPx()
                                        
                                        val path = androidx.compose.ui.graphics.Path().apply {
                                            moveTo(startX, dotY)
                                            cubicTo(
                                                controlX, dotY,
                                                controlX, endY,
                                                startX, endY
                                            )
                                        }
                                        drawPath(path, color, style = androidx.compose.ui.graphics.drawscope.Stroke(width = sw))
                                    }
                                }
                        ) {
                            if (isEvenRow) {
                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                    if (rowProfiles.isNotEmpty()) TimelineNode(rowProfiles[0], onClick = { onPlantClick(rowProfiles[0].plantId) })
                                }
                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                    if (rowProfiles.size > 1) TimelineNode(rowProfiles[1], onClick = { onPlantClick(rowProfiles[1].plantId) })
                                }
                            } else {
                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                    if (rowProfiles.size > 1) TimelineNode(rowProfiles[1], onClick = { onPlantClick(rowProfiles[1].plantId) })
                                }
                                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                    if (rowProfiles.isNotEmpty()) TimelineNode(rowProfiles[0], onClick = { onPlantClick(rowProfiles[0].plantId) })
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "Tap a plant to view full history",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                        )
                    }
                }
            }
        }
    }
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawArrow(x: Float, y: Float, pointsRight: Boolean, color: androidx.compose.ui.graphics.Color, sw: Float) {
    val arrowSize = 12.dp.toPx()
    val path = androidx.compose.ui.graphics.Path().apply {
        if (pointsRight) {
            moveTo(x, y)
            lineTo(x - arrowSize, y - arrowSize)
            moveTo(x, y)
            lineTo(x - arrowSize, y + arrowSize)
        } else {
            moveTo(x, y)
            lineTo(x + arrowSize, y - arrowSize)
            moveTo(x, y)
            lineTo(x + arrowSize, y + arrowSize)
        }
    }
    drawPath(path, color, style = androidx.compose.ui.graphics.drawscope.Stroke(width = sw, cap = androidx.compose.ui.graphics.StrokeCap.Round, join = androidx.compose.ui.graphics.StrokeJoin.Round))
}

@Composable
fun TimelineNode(profile: PlantProfile, onClick: () -> Unit) {
    val sortedScans = profile.scans.sortedByDescending { it.timestamp }
    val dateFormat = remember { SimpleDateFormat("MMM dd", Locale.getDefault()) }
    val view = LocalView.current
    val haptic = LocalHapticFeedback.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(140.dp)
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) // Or LongPress, user said "standard, crisp vibration"
                view.playSoundEffect(SoundEffectConstants.CLICK)
                onClick()
            }
    ) {
        // Stacked image thumbnails
        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            // Show up to 3 stacked thumbnails
            val displayScans = sortedScans.take(3).reversed()
            displayScans.forEachIndexed { index, scan ->
                val offsetX = (index * 8).dp
                val offsetY = -(index * 8).dp
                val imgSize = (90 - index * 6).dp

                Box(
                    modifier = Modifier
                        .offset(x = offsetX, y = offsetY)
                        .size(imgSize)
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardSurface)
                        .border(2.dp, if (index == displayScans.lastIndex) FloraVibrant else CardBorder, RoundedCornerShape(16.dp))
                ) {
                    if (scan.imagePath.isNotEmpty() && File(scan.imagePath).exists()) {
                        val bitmap = remember(scan.imagePath) {
                            BitmapFactory.decodeFile(scan.imagePath)
                        }
                        if (bitmap != null) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = scan.diseaseName,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            PlaceholderThumbnail()
                        }
                    } else {
                        PlaceholderThumbnail()
                    }
                }
            }

            // Scan count badge
            if (sortedScans.size > 1) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 8.dp, y = (-8).dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(FloraVibrant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${sortedScans.size}",
                        style = MaterialTheme.typography.labelSmall,
                        color = DeepForest,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Timeline dot
        Box(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .background(FloraVibrant)
                .border(2.dp, FloraLight, CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Plant name
        Text(
            text = profile.customName,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // Latest status
        val latestScan = sortedScans.firstOrNull()
        if (latestScan != null) {
            Text(
                text = latestScan.diseaseName,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = dateFormat.format(Date(latestScan.timestamp)),
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun PlaceholderThumbnail() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CardBorder),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Eco,
            contentDescription = null,
            tint = FloraVibrant.copy(alpha = 0.4f),
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun EmptyTimelineState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.LocalFlorist,
            contentDescription = null,
            tint = FloraVibrant.copy(alpha = 0.5f),
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Your plant timeline is empty.",
            style = MaterialTheme.typography.titleMedium,
            color = TextPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Scan your first plant to start tracking.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}
