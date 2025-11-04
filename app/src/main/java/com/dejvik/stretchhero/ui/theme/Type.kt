package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Typography system for StretchHero app based on Material 3 Design guidelines.
 *
 * This typography scale follows Material Design 3 specifications:
 * - Display styles: Large, prominent text (typically used for hero sections)
 * - Headline styles: High-emphasis text for section headers
 * - Title styles: Medium-emphasis text for cards and list items
 * - Body styles: Regular text for main content
 * - Label styles: Smaller text for UI components like buttons
 *
 * Letter spacing conventions:
 * - Display styles use TextUnit.Unspecified per Material 3 guidelines (no extra tracking)
 * - Title, body, and label styles use explicit letter spacing values for improved readability
 * - Values are measured in sp (scaled pixels) to respect user font size preferences
 *
 * Font weight usage:
 * - Normal (400): Body text and display styles
 * - Medium (500): Labels that need slight emphasis
 * - SemiBold (600): Headlines and titles for clear hierarchy
 * - Bold (700): Small titles that need strong emphasis
 */

// Use a modern sans-serif font family
// TODO: Consider replacing with custom Montserrat font for better brand consistency
val montserratFont = FontFamily.SansSerif

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    displayMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    displaySmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    headlineLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    headlineMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    headlineSmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    titleLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    titleMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = montserratFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
