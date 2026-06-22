package com.priyanshu.floralens.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun BulletedText(
    text: String,
    color: Color,
    style: TextStyle,
    lineHeight: TextUnit,
    modifier: Modifier = Modifier
) {
    // We split the string by newline to isolate each bullet point
    val items = text.split("\n").filter { it.isNotBlank() }
    Column(modifier = modifier) {
        items.forEach { item ->
            // Remove any existing bullet character and trim
            val cleanItem = item.trim().removePrefix("•").trim()
            Row(
                modifier = Modifier.padding(bottom = 6.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "•",
                    color = color,
                    style = style,
                    lineHeight = lineHeight,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = cleanItem,
                    color = color,
                    style = style,
                    lineHeight = lineHeight
                )
            }
        }
    }
}
