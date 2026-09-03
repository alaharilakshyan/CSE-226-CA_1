package com.example.cse226.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineBasicsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CoroutineBasicsScreen()
                }
            }
        }
    }
}


@Composable
fun CoroutineBasicsScreen() {
    // UI States
    var resultText by remember { mutableStateOf("Press button to start background task") }
    var isLoading by remember { mutableStateOf(false) }

    // Coroutine Scope bound to this Composable's lifecycle
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = resultText, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    isLoading = true
                    resultText = "Fetching data in background..."

                    // 1. Launching a coroutine in response to user interaction
                    coroutineScope.launch {
                        // 2. Calling a suspend function to perform work
                        val data = fetchUserDataFromNetwork()

                        // 3. Updating UI state after coroutine returns result
                        resultText = data
                        isLoading = false
                    }
                }
            ) {
                Text("Start Long Task")
            }
        }
    }
}


suspend fun fetchUserDataFromNetwork(): String {
    // Shift execution off the Main thread onto an I/O optimized thread pool
    return withContext(Dispatchers.IO) {
        // Simulate a 3-second non-blocking network/database delay
        delay(3000L)
        "Data Loaded Successfully (ID: ${System.currentTimeMillis() % 1000})"
    }
}


@Preview(showBackground = true)
@Composable
fun CoroutineBasicsScreenPreview() {
    MaterialTheme {
        CoroutineBasicsScreen()
    }
}