package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Use a generic sans-serif font family to avoid relying on Google Play
// Services resources which are unavailable in this project.
val montserratFont = FontFamily.SansSerif

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = montserratFont),
    displayMedium = TextStyle(fontFamily = montserratFont),
    displaySmall = TextStyle(fontFamily = montserratFont),
    headlineLarge = TextStyle(fontFamily = montserratFont),
    headlineMedium = TextStyle(fontFamily = montserratFont),
    headlineSmall = TextStyle(fontFamily = montserratFont),
    titleLarge = TextStyle(fontFamily = montserratFont),
    titleMedium = TextStyle(fontFamily = montserratFont),
    titleSmall = TextStyle(fontFamily = montserratFont),
    bodyLarge = TextStyle(fontFamily = montserratFont),
    bodyMedium = TextStyle(fontFamily = montserratFont),
    bodySmall = TextStyle(fontFamily = montserratFont),
    labelLarge = TextStyle(fontFamily = montserratFont),
    labelMedium = TextStyle(fontFamily = montserratFont),
    labelSmall = TextStyle(fontFamily = montserratFont)
)
