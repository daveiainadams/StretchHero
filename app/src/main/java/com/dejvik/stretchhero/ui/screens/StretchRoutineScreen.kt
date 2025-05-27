package com.dejvik.stretchhero.ui.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.dejvik.stretchhero.R
import com.dejvik.stretchhero.ui.theme.SoftWhite
import com.dejvik.stretchhero.ui.theme.montserratFont
import java.util.Locale

@Composable
fun StretchRoutineScreen(routineName: String) {
    val steps = when (routineName) {
        "Beginner Full Body Stretch" -> listOf(
            "Neck Rolls – 30 seconds",
            "Shoulder Rolls – 30 seconds",
            "Overhead Side Stretch – 30 seconds per side",
            "Seated Forward Fold – 45 seconds",
            "Supine Spinal Twist – 45 seconds per side"
        )
        "Morning Wake-Up Stretch" -> listOf(
            "Standing Reach and Sway – 30 seconds",
            "Cat-Cow Pose – 45 seconds",
            "Downward Dog – 45 seconds",
            "Low Lunge Stretch – 30 seconds per side",
            "Standing Forward Fold – 30 seconds"
        )
        "Post-Workout Cool Down" -> listOf(
            "Child's Pose – 60 seconds",
            "Kneeling Hip Flexor Stretch – 30 seconds per side",
            "Figure Four Stretch – 30 seconds per side",
            "Standing Quad Stretch – 30 seconds per leg",
            "Cross-Body Shoulder Stretch – 30 seconds per arm"
        )
        "Evening Relaxation Stretch" -> listOf(
            "Legs Up the Wall – 2 minutes",
            "Seated Forward Fold – 1 minute",
            "Butterfly Pose – 1 minute",
            "Reclined Twist – 1 minute per side",
            "Child's Pose – 2 minutes"
        )
        "Lower Back & Hamstring Relief" -> listOf(
            "Knees-to-Chest Pose – 1 minute",
            "Supine Hamstring Stretch – 45 seconds per leg",
            "Cat-Cow Pose – 1 minute",
            "Piriformis Stretch – 30 seconds per side",
            "Seated Forward Fold – 1 minute"
        )
        else -> listOf("Stretch routine not found.")
    }

    fun parseDuration(text: String): Int {
        return when {
            text.contains("2 minutes") -> 120
            text.contains("1 minute") -> 60
            text.contains("60 seconds") -> 60
            text.contains("45 seconds") -> 45
            text.contains("30 seconds") -> 30
            else -> 30
        }
    }

    val context = LocalContext.current
    val tts = remember(context) {
        TextToSpeech(context) { /* you can log init status here if needed */ }
    }.apply {
        language = Locale.UK
    }


    var currentStepIndex by remember { mutableStateOf(0) }
    var timerRunning by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(0) }
    val routineComplete = currentStepIndex >= steps.size

    val currentStepText = steps.getOrElse(currentStepIndex) { "Routine complete!" }

    LaunchedEffect(timerRunning, timeLeft) {
        if (timerRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (timerRunning && timeLeft == 0) {
            timerRunning = false
            if (currentStepIndex < steps.lastIndex) {
                currentStepIndex++
                val nextText = steps[currentStepIndex]
                timeLeft = parseDuration(nextText)
                tts.speak(nextText, TextToSpeech.QUEUE_FLUSH, null, null)
                timerRunning = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = routineName,
            style = MaterialTheme.typography.headlineSmall,
            color = SoftWhite,
            fontFamily = montserratFont
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (routineComplete) {
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Well done! You've completed the routine.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = SoftWhite,
                        fontFamily = montserratFont
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = {
                        currentStepIndex = 0
                        timeLeft = 0
                        timerRunning = false
                    }) {
                        Text("Restart Routine", fontFamily = montserratFont)
                    }
                }
            }
        } else {
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = when (currentStepIndex) {
                            0 -> R.drawable.ic_stretch_neck
                            1 -> R.drawable.ic_stretch_shoulder
                            2 -> R.drawable.ic_stretch_side
                            3 -> R.drawable.ic_stretch_forward_fold
                            4 -> R.drawable.ic_stretch_twist
                            else -> R.drawable.ic_stretch_placeholder
                        }),
                        contentDescription = "Stretch Step",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentStepText,
                        style = MaterialTheme.typography.bodyLarge,
                        color = SoftWhite,
                        fontFamily = montserratFont
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Time Left: $timeLeft seconds",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SoftWhite,
                        fontFamily = montserratFont
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        if (!timerRunning) {
                            timeLeft = parseDuration(currentStepText)
                            tts.speak(currentStepText, TextToSpeech.QUEUE_FLUSH, null, null)
                            timerRunning = true
                        }
                    }) {
                        Text("Start Stretch", fontFamily = montserratFont)
                    }
                }
            }
        }
    }
}
