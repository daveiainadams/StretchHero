package com.dejvik.stretchhero.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.dejvik.stretchhero.utils.getDrawableResourceId
import com.dejvik.stretchhero.utils.capitalizeFirst

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
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = montserratFont,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        
        if (routines.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No routines available",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = montserratFont,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(routines) { routine ->
                    RoutineCard(routine = routine, navController = navController)
                }
            }
        }
    }
}

@Composable
fun RoutineCard(routine: Routine, navController: NavController) {
    val context = LocalContext.current
    val imageResId = remember(routine.id) {
        routine.steps.firstOrNull()?.imageResIdName?.getDrawableResourceId(context) ?: R.drawable.ic_stretch_placeholder
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.RoutineDetail.createRoute(routine.id))
            }
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.size(80.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = routine.name,
                        modifier = Modifier
                            .size(48.dp),
                        contentScale = ContentScale.Fit,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = routine.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${routine.formattedTotalDuration} • ${routine.stepCount} steps",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = montserratFont,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Difficulty Badge
                Surface(
                    color = when(routine.difficulty) {
                        com.dejvik.stretchhero.data.Difficulty.BEGINNER -> MaterialTheme.colorScheme.secondaryContainer
                        com.dejvik.stretchhero.data.Difficulty.INTERMEDIATE -> MaterialTheme.colorScheme.tertiaryContainer
                        com.dejvik.stretchhero.data.Difficulty.ADVANCED -> MaterialTheme.colorScheme.errorContainer
                    },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = routine.difficulty.name.lowercase().capitalizeFirst(),
                        style = MaterialTheme.typography.labelMedium,
                        fontFamily = montserratFont,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = when(routine.difficulty) {
                            com.dejvik.stretchhero.data.Difficulty.BEGINNER -> MaterialTheme.colorScheme.onSecondaryContainer
                            com.dejvik.stretchhero.data.Difficulty.INTERMEDIATE -> MaterialTheme.colorScheme.onTertiaryContainer
                            com.dejvik.stretchhero.data.Difficulty.ADVANCED -> MaterialTheme.colorScheme.onErrorContainer
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StretchLibraryScreenPreview() {
    StretchLibraryScreen(rememberNavController())
}
