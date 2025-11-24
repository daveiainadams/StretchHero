package com.dejvik.stretchhero.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dejvik.stretchhero.ui.theme.montserratFont
import com.dejvik.stretchhero.ui.theme.ralewayFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.Box
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dejvik.stretchhero.navigation.Screen // Will be needed for navigation

@Composable
@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1D2E), // Deep night sky
                        Color(0xFF2D3142), // Charcoal
                        Color(0xFF3D2817)  // Warm ember glow at bottom
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = androidx.compose.foundation.shape.CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.size(160.dp)
            ) {
                Image(
                    painter = painterResource(id = com.dejvik.stretchhero.R.drawable.stretchhero_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentScale = ContentScale.Fit
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "STRETCH HERO",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.tertiary,
                fontFamily = montserratFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
            
            Text(
                text = "Forge Your Flexibility",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                fontFamily = ralewayFont,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Button(
                onClick = {
                    navController.navigate(Screen.StretchLibrary.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    "BEGIN QUEST", 
                    fontFamily = montserratFont,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = {
                    navController.navigate(Screen.Achievements.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f))
            ) {
                Text(
                    "HERO STATS", 
                    fontFamily = montserratFont,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }
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
