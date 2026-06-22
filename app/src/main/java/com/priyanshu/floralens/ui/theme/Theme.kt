package com.priyanshu.floralens.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FloraColorScheme = lightColorScheme(
    primary = FloraVibrant,
    secondary = FloraDark,
    tertiary = FloraLight,
    background = DeepForest,
    surface = CardSurface,
    surfaceVariant = CardBorder,
    onPrimary = DeepForest,
    onSecondary = TextPrimary,
    onTertiary = DeepForest,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary
)

@Composable
fun FloraLensTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FloraColorScheme,
        typography = Typography,
        content = content
    )
}