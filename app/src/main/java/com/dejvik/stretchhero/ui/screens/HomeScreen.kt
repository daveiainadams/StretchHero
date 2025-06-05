package com.dejvik.stretchhero.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dejvik.stretchhero.ui.theme.montserratFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dejvik.stretchhero.navigation.Screen // Will be needed for navigation

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = com.dejvik.stretchhero.R.drawable.stretchhero_logo),
            contentDescription = "App Logo",
            modifier = Modifier.height(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Stretch Hero",
            fontSize = 32.sp,
            fontFamily = montserratFont
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navController.navigate(Screen.StretchLibrary.route)
        }) {
            Text("Begin Stretching", fontFamily = montserratFont)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Preview requires a NavController, even if it's a dummy one for preview purposes.
    // For MaterialTheme to apply, you might need to wrap this in your app's theme if it's not applying.
    // com.dejvik.stretchhero.ui.theme.StretchHeroTheme { // Uncomment if needed for preview
        HomeScreen(navController = rememberNavController())
    // }
}
