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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class LaunchedEffect : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffectScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchedEffectScreen() {
    // Dynamic key: Changing this ID forces LaunchedEffect to cancel its ongoing task and restart
    var userId by remember { mutableIntStateOf(101) }

    // UI States managed during the asynchronous side effect
    var userDetails by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // --- KEY CONCEPT: LaunchedEffect ---
    // Key parameter: 'userId'
    // Whenever 'userId' changes (or when this Composable enters the Composition for the first time),
    // the previous coroutine block is automatically cancelled, and a new one is launched.
    LaunchedEffect(key1 = userId) {
        isLoading = true
        userDetails = "" // Reset state

        // Simulate a network delay (Asynchronous operation inside a Suspend function)
        delay(2000)

        // Update state with fetched data
        userDetails = "User Data for ID: #$userId loaded successfully!"
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LaunchedEffect Demo - CSE226") }
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
                text = "Side Effects in Compose",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Current Target User ID: $userId",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Display loading indicator or fetch result
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (isLoading) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Fetching from server...", fontSize = 12.sp)
                        }
                    } else {
                        Text(
                            text = userDetails,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Trigger buttons to demonstrate key-retriggering behavior
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { userId = 101 }
                ) {
                    Text("Load User 101")
                }

                Button(
                    onClick = { userId = 102 }
                ) {
                    Text("Load User 102")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LaunchedEffectDemoPreview() {
    LaunchedEffectScreen()
}