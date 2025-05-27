package com.dejvik.stretchhero.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api // Added import
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dejvik.stretchhero.R
import com.dejvik.stretchhero.utils.TextToSpeechHelper
import com.dejvik.stretchhero.ui.theme.MutedRed
import com.dejvik.stretchhero.ui.theme.SoftWhite
// import com.dejvik.stretchhero.ui.theme.montserratFont // Commented out as it's replaced by FontFamily.Default
import androidx.compose.ui.text.font.FontFamily // Ensured import

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
                title = { Text(currentRoutine?.name ?: "Loading...", fontFamily = FontFamily.Default, color = SoftWhite) },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.stopTimer()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = SoftWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MutedRed)
            )
        },
        containerColor = Color.DarkGray
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
                    color = SoftWhite,
                    fontFamily = FontFamily.Default,
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
                Text("Loading routine or routine has no steps...", fontSize = 20.sp, color = SoftWhite)
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
                    fontFamily = FontFamily.Default,
                    color = SoftWhite,
                    modifier = Modifier.padding(bottom = 8.dp)
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
                    fontFamily = FontFamily.Default,
                    color = SoftWhite,
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
                        // This functionality needs to be added to ViewModel
                        // For now, let's disable or think about how to implement "previous"
                        // viewModel.moveToPreviousStep() // if implemented
                    },
                    enabled = currentStepIndex > 0 // viewModel.canMoveToPrevious.value
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Step", tint = SoftWhite, modifier = Modifier.size(48.dp))
                }

                IconButton(
                    onClick = {
                        if (isRunning) {
                            viewModel.stopTimer()
                        } else {
                            viewModel.startStepTimer()
                            // TTS is handled by LaunchedEffect(currentStep) when isRunning becomes true
                        }
                    },
                    modifier = Modifier.size(72.dp)
                ) {
                    Icon(
                        imageVector = if (isRunning) Icons.Filled.Refresh else Icons.Filled.PlayArrow, // Should be Pause icon if running
                        contentDescription = if (isRunning) "Pause Timer" else "Start Timer",
                        tint = SoftWhite,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                IconButton(
                    onClick = {
                        viewModel.stopTimer() // Stop current timer before moving
                        viewModel.moveToNextStep()
                         // TTS for next step handled by LaunchedEffect(currentStep)
                    },
                    enabled = currentStepIndex < (currentRoutine.steps.size - 1)
                ) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Next Step", tint = SoftWhite, modifier = Modifier.size(48.dp))
                }
            }
        }
    }
}
