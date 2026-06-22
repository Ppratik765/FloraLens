package com.priyanshu.floralens.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
class FloraColors(
    val floraVibrant: Color,
    val floraLight: Color,
    val floraDark: Color,
    val deepForest: Color,
    val cardSurface: Color,
    val cardBorder: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val softShadow: Color
)

val DarkFloraColors = FloraColors(
    floraVibrant = Color(0xFF14FF6B),
    floraLight = Color(0xFFA6FFCE),
    floraDark = Color(0xFF00C853),
    deepForest = Color(0xFF133621),
    cardSurface = Color(0xFF1D4A2D),
    cardBorder = Color(0xFF286B40),
    textPrimary = Color(0xFFF0FFF5),
    textSecondary = Color(0xFF91E8B4),
    softShadow = Color(0x4D000000)
)

val LightFloraColors = FloraColors(
    floraVibrant = Color(0xFF00C853), // Deep green accent on light mode
    floraLight = Color(0xFF8BDEAA),
    floraDark = Color(0xFF007E33),
    deepForest = Color(0xFFF5FAF6), // Very light background
    cardSurface = Color(0xFFFFFFFF), // Pure white cards
    cardBorder = Color(0xFFE0F2E5), // Soft green borders
    textPrimary = Color(0xFF0D1F12), // Dark text
    textSecondary = Color(0xFF4A7A59), // Medium green text
    softShadow = Color(0x1A000000)
)

// ── Composable accessors for automatic screen morphing ──
val FloraVibrant: Color @Composable get() = FloraTheme.colors.floraVibrant
val FloraLight: Color @Composable get() = FloraTheme.colors.floraLight
val FloraDark: Color @Composable get() = FloraTheme.colors.floraDark
val DeepForest: Color @Composable get() = FloraTheme.colors.deepForest
val CardSurface: Color @Composable get() = FloraTheme.colors.cardSurface
val CardBorder: Color @Composable get() = FloraTheme.colors.cardBorder
val TextPrimary: Color @Composable get() = FloraTheme.colors.textPrimary
val TextSecondary: Color @Composable get() = FloraTheme.colors.textSecondary

// ── Backward-compatible aliases ──
val PremiumWhite: Color @Composable get() = DeepForest
val PureWhite: Color @Composable get() = CardSurface
val PastelGreenCard: Color @Composable get() = CardBorder
val BotanicalGreen: Color @Composable get() = FloraVibrant
val LeafGreen: Color @Composable get() = FloraLight
val OliveGreen: Color @Composable get() = FloraDark
val PastelPink: Color @Composable get() = FloraLight
val PastelYellow: Color @Composable get() = TextPrimary
val TextDark: Color @Composable get() = TextPrimary
val TextLight: Color @Composable get() = TextSecondary
val SoftShadow: Color @Composable get() = FloraTheme.colors.softShadow

// ── Extra named colors for direct use ──
val TurfGreen: Color @Composable get() = FloraDark
val LightGreen: Color @Composable get() = FloraLight
val TeaGreen: Color @Composable get() = TextPrimary
val DarkSpruce: Color @Composable get() = DeepForest
val YellowGreen: Color @Composable get() = TextSecondary