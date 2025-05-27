package com.dejvik.stretchhero.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.dejvik.stretchhero.ui.theme.MutedRed
import com.dejvik.stretchhero.ui.theme.SoftWhite
import com.dejvik.stretchhero.ui.theme.montserratFont

@Composable
fun StretchLibraryScreen(navController: NavController) {
    val routines = listOf(
        "Beginner Full Body Stretch",
        "Morning Wake-Up Stretch",
        "Post-Workout Cool Down",
        "Evening Relaxation Stretch",
        "Lower Back & Hamstring Relief"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose Your Routine",
            style = MaterialTheme.typography.headlineSmall,
            color = SoftWhite,
            fontFamily = montserratFont
        )

        Spacer(modifier = Modifier.height(16.dp))

        routines.forEach { routine ->
            val encodedRoutine = URLEncoder.encode(routine, StandardCharsets.UTF_8.toString())
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("routineDetail/$encodedRoutine")
                    },
                colors = CardDefaults.cardColors(containerColor = MutedRed)
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = routine,
                        style = MaterialTheme.typography.bodyLarge,
                        color = SoftWhite,
                        fontFamily = montserratFont
                    )
                }
            }
        }
    }
}
