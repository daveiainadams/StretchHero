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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.dejvik.stretchhero.ui.theme.montserratFont
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
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Stretch Routines",
            fontSize = 28.sp,
            fontFamily = montserratFont,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
            color = MaterialTheme.colorScheme.onBackground
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
    val context = LocalContext.current
    val imageResId = remember(routine.id) {
        context.resources.getIdentifier(
            routine.steps.firstOrNull()?.imageResIdName ?: "",
            "drawable",
            context.packageName
        ).takeIf { it != 0 } ?: R.drawable.ic_stretch_placeholder
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.RoutineDetail.createRoute(routine.id))
            }
            .clip(RoundedCornerShape(12.dp)), // More rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
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
                    fontFamily = montserratFont,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Estimated time: ${routine.steps.sumOf { it.duration } / 60} min",
                    fontSize = 14.sp,
                    fontFamily = montserratFont,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
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
