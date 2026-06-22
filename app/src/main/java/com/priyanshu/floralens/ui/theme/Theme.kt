package com.priyanshu.floralens.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalFloraColors = staticCompositionLocalOf { DarkFloraColors }

object FloraTheme {
    val colors: FloraColors
        @Composable
        get() = LocalFloraColors.current
}

@Composable
fun FloraLensTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val targetColors = if (darkTheme) DarkFloraColors else LightFloraColors

    val animationSpec = tween<Color>(durationMillis = 800)

    val animatedFloraVibrant by animateColorAsState(targetColors.floraVibrant, animationSpec, label = "ColorAnimation")
    val animatedFloraLight by animateColorAsState(targetColors.floraLight, animationSpec, label = "ColorAnimation")
    val animatedFloraDark by animateColorAsState(targetColors.floraDark, animationSpec, label = "ColorAnimation")
    val animatedDeepForest by animateColorAsState(targetColors.deepForest, animationSpec, label = "ColorAnimation")
    val animatedCardSurface by animateColorAsState(targetColors.cardSurface, animationSpec, label = "ColorAnimation")
    val animatedCardBorder by animateColorAsState(targetColors.cardBorder, animationSpec, label = "ColorAnimation")
    val animatedTextPrimary by animateColorAsState(targetColors.textPrimary, animationSpec, label = "ColorAnimation")
    val animatedTextSecondary by animateColorAsState(targetColors.textSecondary, animationSpec, label = "ColorAnimation")
    val animatedSoftShadow by animateColorAsState(targetColors.softShadow, animationSpec, label = "ColorAnimation")

    val animatedColors = FloraColors(
        floraVibrant = animatedFloraVibrant,
        floraLight = animatedFloraLight,
        floraDark = animatedFloraDark,
        deepForest = animatedDeepForest,
        cardSurface = animatedCardSurface,
        cardBorder = animatedCardBorder,
        textPrimary = animatedTextPrimary,
        textSecondary = animatedTextSecondary,
        softShadow = animatedSoftShadow
    )

    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = animatedFloraVibrant,
            background = animatedDeepForest,
            surface = animatedCardSurface,
            onPrimary = animatedDeepForest,
            onBackground = animatedTextPrimary,
            onSurface = animatedTextPrimary
        )
    } else {
        lightColorScheme(
            primary = animatedFloraVibrant,
            background = animatedDeepForest,
            surface = animatedCardSurface,
            onPrimary = animatedDeepForest,
            onBackground = animatedTextPrimary,
            onSurface = animatedTextPrimary
        )
    }

    CompositionLocalProvider(LocalFloraColors provides animatedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}