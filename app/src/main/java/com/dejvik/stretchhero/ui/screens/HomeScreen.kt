package com.dejvik.stretchhero.ui.screens

import androidx.compose.foundation.Image // Added
import androidx.compose.foundation.background // Added for Modifier.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth // Added
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size // Added
import androidx.compose.foundation.shape.RoundedCornerShape // Added
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // Added
import androidx.compose.material3.MaterialTheme // Keep if using MaterialTheme.typography or colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Added
import androidx.compose.ui.res.painterResource // Added
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dejvik.stretchhero.R // Needed for R.drawable
import com.dejvik.stretchhero.navigation.Screen
// import com.dejvik.stretchhero.ui.theme.montserratFont // Keep commented if not using custom font
// import androidx.compose.ui.text.font.FontFamily // Not needed as fontFamily is not set explicitly

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8)) // Light grayish-blue background
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.stretchhero_logo),
            contentDescription = "Stretch Hero Logo",
            modifier = Modifier
                .size(150.dp) // Adjust size as needed
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Stretch Hero",
            fontSize = 36.sp, // Increased size
            fontWeight = FontWeight.Bold, // Make it bold
            color = Color(0xFF333333) // Darker text color
            // fontFamily = FontFamily.Default // Will use theme default
        )
        Spacer(modifier = Modifier.height(48.dp)) // Increased spacing
        Button(
            onClick = {
                navController.navigate(Screen.StretchLibrary.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.8f) // Button takes 80% of width
                .height(56.dp), // Increased button height
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007BFF), // A vibrant blue
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp) // Rounded corners
        ) {
            Text("Begin Stretching", fontSize = 18.sp) // Slightly larger button text
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // To see background color in preview, ensure the theme is applied or set a background to the preview composable
    // com.dejvik.stretchhero.ui.theme.StretchHeroTheme { // Assuming StretchHeroTheme sets a surface color
        HomeScreen(navController = rememberNavController())
    // }
}
