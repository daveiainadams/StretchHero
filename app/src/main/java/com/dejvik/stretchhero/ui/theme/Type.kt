package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font // Uncommented
import androidx.compose.ui.text.googlefonts.FontProvider // Uncommented
import androidx.compose.ui.text.googlefonts.GoogleFont // Uncommented
import androidx.compose.ui.unit.sp
import com.dejvik.stretchhero.R

// Uncommented montserratFont definition
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
