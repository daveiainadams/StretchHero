package com.dejvik.stretchhero.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.dejvik.stretchhero.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val montserratFont = FontFamily(
    Font(googleFont = GoogleFont("Montserrat"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("Montserrat"), fontProvider = provider, weight = FontWeight.Bold)
)

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
