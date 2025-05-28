package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font // Added
import androidx.compose.ui.text.font.FontWeight // Added
// import androidx.compose.ui.text.googlefonts.Font // Removed
// import androidx.compose.ui.text.googlefonts.FontProvider // Removed
// import androidx.compose.ui.text.googlefonts.GoogleFont // Removed
import androidx.compose.ui.unit.sp
import com.dejvik.stretchhero.R

// Updated montserratFont definition
val montserratFont = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

// Define the Typography object
val Typography = Typography(
    displayLarge = TextStyle(fontFamily = montserratFont),
    displayMedium = TextStyle(fontFamily = montserratFont),
    displaySmall = TextStyle(fontFamily = montserratFont),
    headlineLarge = TextStyle(fontFamily = montserratFont),
    headlineMedium = TextStyle(fontFamily = montserratFont),
    headlineSmall = TextStyle(fontFamily = montserratFont), // As used in StretchLibraryScreen
    titleLarge = TextStyle(fontFamily = montserratFont),
    titleMedium = TextStyle(fontFamily = montserratFont),
    titleSmall = TextStyle(fontFamily = montserratFont),
    bodyLarge = TextStyle(fontFamily = montserratFont),    // As used in StretchLibraryScreen & StretchRoutineScreen
    bodyMedium = TextStyle(fontFamily = montserratFont),   // As used in StretchRoutineScreen
    bodySmall = TextStyle(fontFamily = montserratFont),
    labelLarge = TextStyle(fontFamily = montserratFont),
    labelMedium = TextStyle(fontFamily = montserratFont),
    labelSmall = TextStyle(fontFamily = montserratFont)
)
