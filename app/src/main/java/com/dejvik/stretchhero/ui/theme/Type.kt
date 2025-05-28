package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily // Ensured
// import androidx.compose.ui.text.font.Font // Removed
// import androidx.compose.ui.text.font.FontWeight // Removed
// import androidx.compose.ui.text.googlefonts.Font // Was already removed
// import androidx.compose.ui.text.googlefonts.FontProvider // Was already removed
// import androidx.compose.ui.text.googlefonts.GoogleFont // Was already removed
import androidx.compose.ui.unit.sp
// import com.dejvik.stretchhero.R // Removed as it was only for R.font

/* Commented out montserratFont definition
val montserratFont = FontFamily(
    Font(com.dejvik.stretchhero.R.font.montserrat_regular, FontWeight.Normal),
    Font(com.dejvik.stretchhero.R.font.montserrat_bold, FontWeight.Bold)
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
