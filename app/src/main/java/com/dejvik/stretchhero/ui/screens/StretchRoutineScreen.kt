package com.dejvik.stretchhero.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dejvik.stretchhero.R
import com.dejvik.stretchhero.navigation.Screen
import com.dejvik.stretchhero.utils.TextToSpeechHelper
import com.dejvik.stretchhero.utils.getDrawableResourceId
import com.dejvik.stretchhero.ui.theme.montserratFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StretchRoutineScreen(
    navController: NavController,
    routineId: String,
    viewModel: RoutineViewModel = viewModel()
) {
    val context = LocalContext.current
    val ttsHelper = remember { TextToSpeechHelper(context) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(routineId) {
        viewModel.loadRoutine(routineId)
    }

    val currentRoutine = uiState.currentRoutine
    val currentStepIndex = uiState.currentStepIndex
    val timeLeft = uiState.timeLeftInSeconds
    val isRunning = uiState.timerRunning
    val routineComplete = uiState.routineComplete
    val routineFound = uiState.routineFound
    val isLoading = uiState.isLoading
    val error = uiState.error

    val currentStep = currentRoutine?.steps?.getOrNull(currentStepIndex)

    // Animation for timer
    val animatedTimeLeft by animateIntAsState(
        targetValue = timeLeft,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    // Speak when the step changes
    LaunchedEffect(currentStep) {
        currentStep?.name?.let {
            if (isRunning || timeLeft == currentStep.duration) {
                ttsHelper.speak(it)
            }
        }
    }
    
    LaunchedEffect(routineComplete) {
        if (routineComplete) {
            ttsHelper.speak("Routine complete. Well done!")
            navController.popBackStack()
        }
    }

    DisposableEffect(ttsHelper) {
        onDispose {
            viewModel.stopTimer()
            ttsHelper.shutdown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        currentRoutine?.name ?: "Loading...", 
                        fontFamily = montserratFont, 
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.stopTimer()
                        navController.navigate(Screen.Home.route) { 
                            popUpTo(Screen.Home.route) { inclusive = true }
                            launchSingleTop = true 
                        }
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack, 
                            contentDescription = "Back", 
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    if (error != null) {
                        IconButton(onClick = { viewModel.clearError() }) {
                            Icon(
                                Icons.Filled.Refresh,
                                contentDescription = "Retry",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        )
                    )
                )
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
                
                error != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Error",
                            style = MaterialTheme.typography.headlineMedium,
                            fontFamily = montserratFont,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = error.toUserMessage(),
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = montserratFont,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
                        )
                        Button(onClick = { viewModel.loadRoutine(routineId) }) {
                            Text("Retry", fontFamily = montserratFont)
                        }
                    }
                }
                
                !routineFound -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Routine not found. Please go back and select a valid routine.",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = montserratFont
                        )
                    }
                }
                
                currentStep == null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Preparing routine...", 
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground, 
                            fontFamily = montserratFont
                        )
                    }
                }
                
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Top Section: Step Info & Image
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = currentStep.name,
                                style = MaterialTheme.typography.headlineMedium,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            
                            LinearProgressIndicator(
                                progress = (currentStepIndex + 1f) / currentRoutine!!.steps.size,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                            
                            Text(
                                text = "Step ${currentStepIndex + 1} of ${currentRoutine.steps.size}",
                                style = MaterialTheme.typography.labelLarge,
                                fontFamily = montserratFont,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            val imageResId = remember(currentStep.imageResIdName) {
                                currentStep.imageResIdName.getDrawableResourceId(context).takeIf { it != 0 } ?: R.drawable.ic_stretch_placeholder
                            }

                            Card(
                                modifier = Modifier
                                    .padding(vertical = 24.dp)
                                    .size(280.dp),
                                shape = RoundedCornerShape(24.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = imageResId),
                                        contentDescription = "Stretch Image: ${currentStep.name}",
                                        modifier = Modifier
                                            .size(120.dp),
                                        contentScale = ContentScale.Fit,
                                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                    )
                                }
                            }
                        }

                        // Bottom Section: Timer & Controls
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = if (isRunning || timeLeft < currentStep.duration) "$animatedTimeLeft" else "${currentStep.duration}",
                                style = MaterialTheme.typography.displayLarge,
                                fontSize = 80.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "seconds",
                                style = MaterialTheme.typography.titleMedium,
                                fontFamily = montserratFont,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 32.dp)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = {
                                        viewModel.stopTimer()
                                        viewModel.moveToPreviousStep()
                                    },
                                    enabled = currentStepIndex > 0,
                                    modifier = Modifier
                                        .size(56.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                            shape = androidx.compose.foundation.shape.CircleShape
                                        )
                                ) {
                                    Icon(
                                        Icons.Filled.ArrowBack, 
                                        contentDescription = "Previous Step", 
                                        tint = MaterialTheme.colorScheme.onSurface, 
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                Button(
                                    onClick = {
                                        if (isRunning) {
                                            viewModel.stopTimer()
                                        } else {
                                            viewModel.startStepTimer()
                                        }
                                    },
                                    modifier = Modifier.size(88.dp),
                                    shape = androidx.compose.foundation.shape.CircleShape,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isRunning) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                        contentDescription = if (isRunning) "Pause Timer" else "Start Timer",
                                        modifier = Modifier.size(40.dp)
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        viewModel.stopTimer()
                                        viewModel.moveToNextStep()
                                    },
                                    enabled = currentStepIndex < (currentRoutine.steps.size - 1),
                                    modifier = Modifier
                                        .size(56.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                            shape = androidx.compose.foundation.shape.CircleShape
                                        )
                                ) {
                                    Icon(
                                        Icons.Filled.ArrowForward, 
                                        contentDescription = "Next Step", 
                                        tint = MaterialTheme.colorScheme.onSurface, 
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
