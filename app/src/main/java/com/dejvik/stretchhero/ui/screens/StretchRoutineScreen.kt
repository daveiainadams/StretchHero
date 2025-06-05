package com.dejvik.stretchhero.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api // Added import
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
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
import com.dejvik.stretchhero.navigation.Screen // Added import
import com.dejvik.stretchhero.utils.TextToSpeechHelper
import com.dejvik.stretchhero.ui.theme.montserratFont

@OptIn(ExperimentalMaterial3Api::class) // Added annotation
@Composable
fun StretchRoutineScreen(
    navController: NavController,
    routineId: String,
    viewModel: RoutineViewModel = viewModel()
) {
    val context = LocalContext.current
    val ttsHelper = remember { TextToSpeechHelper(context) }

    LaunchedEffect(routineId) {
        viewModel.loadRoutine(routineId)
    }

    val currentRoutine = viewModel.currentRoutine.value
    val currentStepIndex = viewModel.currentStepIndex.value
    val timeLeft = viewModel.timeLeftInSeconds.value
    val isRunning = viewModel.timerRunning.value
    val routineComplete = viewModel.routineComplete.value
    val routineFound = viewModel.routineFound.value

    val currentStep = currentRoutine?.steps?.getOrNull(currentStepIndex)

    // Animation for timer
    val animatedTimeLeft by animateIntAsState(
        targetValue = timeLeft,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    // Speak when the step changes
    LaunchedEffect(currentStep) {
        currentStep?.name?.let {
            if (isRunning || timeLeft == currentStep.duration) { // Speak if timer just started or step changed while timer was running
                 ttsHelper.speak(it)
            }
        }
    }
     LaunchedEffect(routineComplete) {
        if (routineComplete) {
            ttsHelper.speak("Routine complete. Well done!")
            navController.popBackStack() // Navigate back when routine is done
        }
    }


    DisposableEffect(ttsHelper) {
        onDispose {
            viewModel.stopTimer() // Stop timer if screen is disposed
            ttsHelper.shutdown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentRoutine?.name ?: "Loading...", fontFamily = montserratFont, color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.stopTimer()
                        navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true }; launchSingleTop = true }
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        if (!routineFound) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Routine not found. Please go back and select a valid routine.",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = montserratFont,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
            return@Scaffold
        }

        if (currentStep == null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // This case should ideally be less frequent if routineFound handles the primary "not found"
                // Still, good for robustness if a loaded routine has no steps.
                Text("Loading routine or routine has no steps...", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground, fontFamily = montserratFont)
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = currentStep.name,
                    fontSize = 24.sp,
                    fontFamily = montserratFont,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Step ${currentStepIndex + 1} of ${currentRoutine!!.steps.size}",
                    fontFamily = montserratFont,
                    color = MaterialTheme.colorScheme.onBackground
                )
                LinearProgressIndicator(
                    progress = (currentStepIndex + 1f) / currentRoutine!!.steps.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                val imageResId = remember(currentStep.imageResIdName) {
                    context.resources.getIdentifier(
                        currentStep.imageResIdName,
                        "drawable",
                        context.packageName
                    ).takeIf { it != 0 } ?: R.drawable.ic_stretch_placeholder
                }

                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Stretch Image: ${currentStep.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(vertical = 16.dp)
                        .alpha(0.8f)
                )
                Text(
                    // Display the animated time, or the step duration if timer not running and it's full
                    text = if (isRunning || timeLeft < currentStep.duration) "$animatedTimeLeft s" else "${currentStep.duration} s",
                    fontSize = 48.sp,
                    fontFamily = montserratFont,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        viewModel.stopTimer()
                        viewModel.moveToPreviousStep()
                    },
                    enabled = currentStepIndex > 0
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Step", tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(48.dp))
                }

                IconButton(
                    onClick = {
                        if (isRunning) {
                            viewModel.stopTimer()
                        } else {
                            viewModel.startStepTimer()
                        }
                    },
                    modifier = Modifier.size(72.dp)
                ) {
                    Icon(
                        imageVector = if (isRunning) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (isRunning) "Pause Timer" else "Start Timer",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                IconButton(
                    onClick = {
                        viewModel.stopTimer()
                        viewModel.moveToNextStep()
                    },
                    enabled = currentStepIndex < (currentRoutine.steps.size - 1)
                ) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Next Step", tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(48.dp))
                }
            }
        }
    }
}
