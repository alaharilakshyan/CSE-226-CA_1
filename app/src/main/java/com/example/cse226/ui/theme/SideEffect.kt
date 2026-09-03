package com.example.cse226.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Simulated non-Compose external system (e.g., Analytics SDK, Crashlytics, or Legacy Library)
object ExternalAnalyticsSystem {
    var loggedCounterValue: Int = 0
}

class SideEffect : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SideEffectScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideEffectScreen() {
    // Compose State
    var count by remember { mutableIntStateOf(0) }

    // --- KEY CONCEPT: SideEffect ---
    // Executes AFTER EVERY SUCCESSFUL RECOMPOSITION.
    // Used to publish Compose state updates to external, non-Compose objects.
    SideEffect {
        ExternalAnalyticsSystem.loggedCounterValue = count
        println("CSE226_LOG: [SideEffect] Recomposition successful! Synced external system value = ${ExternalAnalyticsSystem.loggedCounterValue}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SideEffect Demo - CSE226") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "State Synchronization with SideEffect",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "SideEffect runs on every successful recomposition to sync Compose state with external non-Compose objects.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Display Compose State
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Current State Value: $count",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "External System Value: ${ExternalAnalyticsSystem.loggedCounterValue}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Trigger Recomposition by mutating state
            Button(
                onClick = { count++ },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("Increment Counter (+1)")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SideEffectPreview() {
    SideEffectScreen()
}