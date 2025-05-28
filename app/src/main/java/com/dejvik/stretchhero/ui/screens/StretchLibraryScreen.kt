package com.dejvik.stretchhero.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
// import androidx.compose.ui.text.font.FontFamily // Commented out
import com.dejvik.stretchhero.ui.theme.montserratFont // Added import
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dejvik.stretchhero.R
import com.dejvik.stretchhero.data.Routine
import com.dejvik.stretchhero.data.RoutineDataSource
import com.dejvik.stretchhero.navigation.Screen


@Composable
fun StretchLibraryScreen(navController: NavController) {
    val routines = RoutineDataSource.getAllRoutines()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8)) // Light background color
            .padding(16.dp)
    ) {
        Text(
            text = "Stretch Routines",
            fontSize = 28.sp,
            fontFamily = montserratFont, // Changed to montserratFont
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
            color = Color(0xFF333333) // Darker text color
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(routines) { routine ->
                RoutineCard(routine = routine, navController = navController)
            }
        }
    }
}

@Composable
fun RoutineCard(routine: Routine, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.RoutineDetail.createRoute(routine.id))
            }
            .clip(RoundedCornerShape(12.dp)), // More rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder for dynamic image loading based on routine
            // For now, using a generic icon for all routines in the library view
            Image(
                painter = painterResource(id = R.drawable.ic_stretch_placeholder), // Replace with actual dynamic logic if needed
                contentDescription = routine.name,
                modifier = Modifier
                    .size(80.dp) // Slightly larger image
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = routine.name,
                    fontSize = 20.sp, // Slightly larger title
                    fontFamily = montserratFont, // Changed to montserratFont
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Estimated time: ${routine.steps.sumOf { it.duration } / 60} min",
                    fontSize = 14.sp,
                    fontFamily = montserratFont, // Changed to montserratFont
                    color = Color.Gray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StretchLibraryScreenPreview() {
    StretchLibraryScreen(rememberNavController())
}
