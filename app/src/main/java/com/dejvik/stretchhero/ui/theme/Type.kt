package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
// import androidx.compose.ui.text.googlefonts.Font // Commented out
// import androidx.compose.ui.text.googlefonts.FontProvider // Commented out
// import androidx.compose.ui.text.googlefonts.GoogleFont // Commented out
import androidx.compose.ui.unit.sp
import com.dejvik.stretchhero.R

/* Commented out montserratFont definition
val montserratFont = FontFamily(
    Font(
        googleFont = GoogleFont("Montserrat"),
        fontProvider = FontProvider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        )
    )
)
*/

// Define the Typography object
val Typography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.Default),
    displayMedium = TextStyle(fontFamily = FontFamily.Default),
    displaySmall = TextStyle(fontFamily = FontFamily.Default),
    headlineLarge = TextStyle(fontFamily = FontFamily.Default),
    headlineMedium = TextStyle(fontFamily = FontFamily.Default),
    headlineSmall = TextStyle(fontFamily = FontFamily.Default), // As used in StretchLibraryScreen
    titleLarge = TextStyle(fontFamily = FontFamily.Default),
    titleMedium = TextStyle(fontFamily = FontFamily.Default),
    titleSmall = TextStyle(fontFamily = FontFamily.Default),
    bodyLarge = TextStyle(fontFamily = FontFamily.Default),    // As used in StretchLibraryScreen & StretchRoutineScreen
    bodyMedium = TextStyle(fontFamily = FontFamily.Default),   // As used in StretchRoutineScreen
    bodySmall = TextStyle(fontFamily = FontFamily.Default),
    labelLarge = TextStyle(fontFamily = FontFamily.Default),
    labelMedium = TextStyle(fontFamily = FontFamily.Default),
    labelSmall = TextStyle(fontFamily = FontFamily.Default)
)
