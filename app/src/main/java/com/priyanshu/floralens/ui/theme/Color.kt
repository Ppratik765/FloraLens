package com.priyanshu.floralens.ui.theme

import androidx.compose.ui.graphics.Color

// ── FloraLens V5 Vibrant Palette ──
val FloraVibrant = Color(0xFF2AFF73)       // Primary accent
val FloraLight = Color(0xFFC0FFDB)         // Lighter tint for highlights
val FloraDark = Color(0xFF00C853)          // Darker shade for borders/pressed states
val DeepForest = Color(0xFF0D1F12)         // Dark background
val CardSurface = Color(0xFF142E1A)        // Card/surface backgrounds
val CardBorder = Color(0xFF1E4A28)         // Card borders/accent containers
val TextPrimary = Color(0xFFE8FFF0)        // Main text — high contrast on dark
val TextSecondary = Color(0xFF8BDEAA)      // Secondary text

// ── Backward-compatible aliases ──
val PremiumWhite = DeepForest              // Primary background
val PureWhite = CardSurface               // Card/Surface backgrounds
val PastelGreenCard = CardBorder           // Inner card/accent container background
val BotanicalGreen = FloraVibrant          // Primary vibrant accent
val LeafGreen = FloraLight                 // Title/heading accent
val OliveGreen = FloraDark                 // Tertiary / pressed accent
val PastelPink = FloraLight                // Floral accents (now mint)
val PastelYellow = TextPrimary             // Highlights/Treatment text
val TextDark = TextPrimary                 // Main text color
val TextLight = TextSecondary              // Secondary text
val SoftShadow = Color(0x4D000000)         // Shadow color

// ── Extra named colors for direct use ──
val TurfGreen = FloraDark
val LightGreen = FloraLight
val TeaGreen = TextPrimary
val DarkSpruce = DeepForest
val YellowGreen = TextSecondary