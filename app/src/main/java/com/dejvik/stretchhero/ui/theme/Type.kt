package com.dejvik.stretchhero.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.FontProvider
import com.dejvik.stretchhero.R

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
