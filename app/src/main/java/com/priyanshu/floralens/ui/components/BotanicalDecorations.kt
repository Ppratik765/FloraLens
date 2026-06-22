package com.priyanshu.floralens.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.priyanshu.floralens.ui.theme.BotanicalDarkGreen
import com.priyanshu.floralens.ui.theme.BotanicalGreen
import com.priyanshu.floralens.ui.theme.BotanicalLightGreen

@Composable
fun BotanicalBackgroundDecorations() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Top Left Placeholder Leaf
        Icon(
            imageVector = Icons.Filled.Eco,
            contentDescription = null,
            tint = BotanicalLightGreen.copy(alpha = 0.3f),
            modifier = Modifier
                .size(150.dp)
                .offset(x = (-30).dp, y = (-30).dp)
                .rotate(135f)
                .align(Alignment.TopStart)
        )

        // Bottom Right Placeholder Leaf
        Icon(
            imageVector = Icons.Filled.Eco,
            contentDescription = null,
            tint = BotanicalGreen.copy(alpha = 0.2f),
            modifier = Modifier
                .size(200.dp)
                .offset(x = 40.dp, y = 40.dp)
                .rotate(-45f)
                .align(Alignment.BottomEnd)
        )

        // Organic Canvas Vine Drawing
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                moveTo(size.width, size.height * 0.3f)
                quadraticTo(
                    size.width * 0.8f, size.height * 0.5f,
                    size.width, size.height * 0.7f
                )
            }
            drawPath(
                path = path,
                color = BotanicalDarkGreen.copy(alpha = 0.1f),
                style = Stroke(width = 8.dp.toPx())
            )
        }
    }
}
