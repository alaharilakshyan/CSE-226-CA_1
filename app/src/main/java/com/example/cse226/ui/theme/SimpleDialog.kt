package com.example.cse226.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class SimpleDialog : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleDialogScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDialogScreen() {
    // State to control dialog visibility
    var isDialogOpen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Basic Dialog Demo - CSE226") }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            // Button to open the basic dialog
            Button(onClick = { isDialogOpen = true }) {
                Text("Open Basic Dialog")
            }

            // Render the raw Dialog when state is true
            if (isDialogOpen) {
                Dialog(
                    onDismissRequest = { isDialogOpen = false }
                ) {
                    // Container: Gives our basic dialog shape and background color
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 6.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(0.85f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Simple Dialog",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "This is a basic raw Dialog component in Jetpack Compose without predefined slots.",
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            TextButton(
                                onClick = { isDialogOpen = false },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("Close")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimpleDialogPreview() {
    SimpleDialogScreen()
}