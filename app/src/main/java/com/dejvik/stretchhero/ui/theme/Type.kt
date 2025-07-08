package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Use a modern sans-serif font family
val montserratFont = FontFamily.SansSerif

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = androidx.compose.ui.unit.sp(57),
        lineHeight = androidx.compose.ui.unit.sp(64),
        letterSpacing = androidx.compose.ui.unit.sp(-0.25)
    ),
    displayMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = androidx.compose.ui.unit.sp(45),
        lineHeight = androidx.compose.ui.unit.sp(52),
        letterSpacing = androidx.compose.ui.unit.sp(0)
    ),
    displaySmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = androidx.compose.ui.unit.sp(36),
        lineHeight = androidx.compose.ui.unit.sp(44),
        letterSpacing = androidx.compose.ui.unit.sp(0)
    ),
    headlineLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = androidx.compose.ui.unit.sp(32),
        lineHeight = androidx.compose.ui.unit.sp(40),
        letterSpacing = androidx.compose.ui.unit.sp(0)
    ),
    headlineMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = androidx.compose.ui.unit.sp(28),
        lineHeight = androidx.compose.ui.unit.sp(36),
        letterSpacing = androidx.compose.ui.unit.sp(0)
    ),
    headlineSmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = androidx.compose.ui.unit.sp(24),
        lineHeight = androidx.compose.ui.unit.sp(32),
        letterSpacing = androidx.compose.ui.unit.sp(0)
    ),
    titleLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = androidx.compose.ui.unit.sp(22),
        lineHeight = androidx.compose.ui.unit.sp(28),
        letterSpacing = androidx.compose.ui.unit.sp(0)
    ),
    titleMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = androidx.compose.ui.unit.sp(16),
        lineHeight = androidx.compose.ui.unit.sp(24),
        letterSpacing = androidx.compose.ui.unit.sp(0.15)
    ),
    titleSmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Bold,
        fontSize = androidx.compose.ui.unit.sp(14),
        lineHeight = androidx.compose.ui.unit.sp(20),
        letterSpacing = androidx.compose.ui.unit.sp(0.1)
    ),
    bodyLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = androidx.compose.ui.unit.sp(16),
        lineHeight = androidx.compose.ui.unit.sp(24),
        letterSpacing = androidx.compose.ui.unit.sp(0.5)
    ),
    bodyMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = androidx.compose.ui.unit.sp(14),
        lineHeight = androidx.compose.ui.unit.sp(20),
        letterSpacing = androidx.compose.ui.unit.sp(0.25)
    ),
    bodySmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = androidx.compose.ui.unit.sp(12),
        lineHeight = androidx.compose.ui.unit.sp(16),
        letterSpacing = androidx.compose.ui.unit.sp(0.4)
    ),
    labelLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Medium,
        fontSize = androidx.compose.ui.unit.sp(14),
        lineHeight = androidx.compose.ui.unit.sp(20),
        letterSpacing = androidx.compose.ui.unit.sp(0.1)
    ),
    labelMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Medium,
        fontSize = androidx.compose.ui.unit.sp(12),
        lineHeight = androidx.compose.ui.unit.sp(16),
        letterSpacing = androidx.compose.ui.unit.sp(0.5)
    ),
    labelSmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Medium,
        fontSize = androidx.compose.ui.unit.sp(11),
        lineHeight = androidx.compose.ui.unit.sp(16),
        letterSpacing = androidx.compose.ui.unit.sp(0.5)
    )
)
