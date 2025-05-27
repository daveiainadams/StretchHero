package com.dejvik.stretchhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.dejvik.stretchhero.navigation.StretchHeroNavHost
import com.dejvik.stretchhero.ui.theme.StretchHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StretchHeroTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    StretchHeroNavHost()
                }
            }
        }
    }
}
