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

class DisposableEffect : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisposableEffectScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisposableEffectScreen() {
    var isObserverActive by remember () { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text ("DisposableEffect Demo - CSE226")}
            )
        }
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Resource Cleanup with DisposableEffect",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Toggle the observer component below to see setup and cleanup logs.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { isObserverActive = !isObserverActive }
            ) {
                Text( if (isObserverActive) "Stop Sensor Observer" else "Start Sensor Observer")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if(isObserverActive) {
                SensorObserverComponent()
            } else {
                Text(
                    text = "Observer is currently inactive.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}


//A Composable that simulates attaching and detaching a resource/listener (e.g., Sensor, BroadcastReceiver, LocationManager).
@Composable
fun SensorObserverComponent() {

    var statusMessage by remember { mutableStateOf("Initializing...") }

    DisposableEffect(Unit) {
        println("CSE226_Log: Sensor Listener registered successfully")
        statusMessage = "Sensor Listener registered successfully"

        onDispose {
            println("CSE226_Log: Sensor Listener unregistered successfully")
        }
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = statusMessage,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DisposableEffectPreview() {
    DisposableEffectScreen()
}