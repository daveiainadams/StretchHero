package com.dejvik.stretchhero.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dejvik.stretchhero.data.UserPreferencesRepository
import com.dejvik.stretchhero.navigation.Screen
import com.dejvik.stretchhero.ui.theme.montserratFont
import com.dejvik.stretchhero.ui.theme.ralewayFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember { UserPreferencesRepository(context) }
    val scope = rememberCoroutineScope()
    
    var showResetDialog by remember { mutableStateOf(false) }
    var ttsEnabled by remember { mutableStateOf(true) } // TODO: Persist this
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "SETTINGS",
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Audio Section
            Text(
                "AUDIO",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = montserratFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Voice Announcements",
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = ralewayFont,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "Hear exercise names during routines",
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = ralewayFont,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = ttsEnabled,
                        onCheckedChange = { ttsEnabled = it }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Progress Section
            Text(
                "PROGRESS",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = montserratFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Reset All Progress",
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = ralewayFont,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        "This will delete your streak, achievements, and completed challenges. This action cannot be undone.",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = ralewayFont,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    OutlinedButton(
                        onClick = { showResetDialog = true },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text(
                            "RESET PROGRESS",
                            fontFamily = montserratFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // About Section
            Text(
                "ABOUT",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = montserratFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Stretch Hero",
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Version 1.0.0",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = ralewayFont,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Forge your flexibility through the ancient art of stretching. Complete quests, unlock achievements, and become a legendary warrior of mobility.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = ralewayFont,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
    
    // Reset Confirmation Dialog
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            icon = {
                Icon(
                    Icons.Filled.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    "Reset All Progress?",
                    fontFamily = montserratFont,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    "This will permanently delete:\n\n• Your current streak\n• All unlocked achievements\n• Completed challenges\n• Total routines count\n\nThis action cannot be undone.",
                    fontFamily = ralewayFont
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            repository.updateProgress(
                                com.dejvik.stretchhero.data.UserProgress()
                            )
                            showResetDialog = false
                            navController.popBackStack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("RESET", fontFamily = montserratFont, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("CANCEL", fontFamily = montserratFont, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}
