package com.priyanshu.floralens.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.priyanshu.floralens.ui.theme.BotanicalGreen
import com.priyanshu.floralens.ui.theme.LeafGreen
import com.priyanshu.floralens.ui.theme.OliveGreen
import com.priyanshu.floralens.ui.theme.PastelPink
import com.priyanshu.floralens.ui.theme.PastelYellow

@Composable
fun BotanicalBackgroundDecorations() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Top Left Placeholder Leaf
        Icon(
            imageVector = Icons.Filled.Eco,
            contentDescription = null,
            tint = PastelPink.copy(alpha = 0.15f),
            modifier = Modifier
                .size(180.dp)
                .offset(x = (-40).dp, y = (-40).dp)
                .rotate(135f)
                .align(Alignment.TopStart)
        )

        // Top Right Placeholder Flower
        Icon(
            imageVector = Icons.Filled.LocalFlorist,
            contentDescription = null,
            tint = PastelPink.copy(alpha = 0.12f),
            modifier = Modifier
                .size(150.dp)
                .offset(x = 30.dp, y = (-30).dp)
                .rotate(30f)
                .align(Alignment.TopEnd)
        )

        // Bottom Left Placeholder Flower
        Icon(
            imageVector = Icons.Filled.LocalFlorist,
            contentDescription = null,
            tint = PastelYellow.copy(alpha = 0.12f),
            modifier = Modifier
                .size(160.dp)
                .offset(x = (-40).dp, y = 60.dp)
                .rotate(-15f)
                .align(Alignment.BottomStart)
        )

        // Bottom Right Placeholder Leaf
        Icon(
            imageVector = Icons.Filled.Eco,
            contentDescription = null,
            tint = PastelYellow.copy(alpha = 0.15f),
            modifier = Modifier
                .size(220.dp)
                .offset(x = 60.dp, y = 60.dp)
                .rotate(-45f)
                .align(Alignment.BottomEnd)
        )

        // Small Accent Flower Top-Left
        Icon(
            imageVector = Icons.Filled.LocalFlorist,
            contentDescription = null,
            tint = BotanicalGreen.copy(alpha = 0.1f),
            modifier = Modifier
                .size(70.dp)
                .offset(x = 120.dp, y = 80.dp)
                .rotate(45f)
                .align(Alignment.TopStart)
        )

        // Organic Canvas Vine Drawing
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path1 = Path().apply {
                moveTo(size.width, size.height * 0.2f)
                quadraticTo(
                    size.width * 0.7f, size.height * 0.4f,
                    size.width, size.height * 0.6f
                )
            }
            drawPath(
                path = path1,
                color = LeafGreen.copy(alpha = 0.15f),
                style = Stroke(width = 12.dp.toPx())
            )
            
            val path2 = Path().apply {
                moveTo(0f, size.height * 0.5f)
                quadraticTo(
                    size.width * 0.3f, size.height * 0.7f,
                    0f, size.height * 0.9f
                )
            }
            drawPath(
                path = path2,
                color = OliveGreen.copy(alpha = 0.15f),
                style = Stroke(width = 8.dp.toPx())
            )
        }
    }
}

