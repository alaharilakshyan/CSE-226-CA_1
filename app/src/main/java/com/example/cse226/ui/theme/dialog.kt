package com.example.cse226.ui.theme

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


// 1. ACTIVITY CLASS
class Dialog : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                DialogDemoScreen()
            }
        }
    }
}


// 2. MAIN SCREEN COMPOSABLE
@Composable
fun DialogDemoScreen() {
    val context = LocalContext.current

    // State variables to control dialog visibility
    var showAlertDialog by remember { mutableStateOf(false) }
    var showCustomDialog by remember { mutableStateOf(false) }

    // State variable for custom dialog input
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "CSE226 - Dialogs Demo",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { showAlertDialog = true },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Show Alert Dialog")
            }

            OutlinedButton(
                onClick = { showCustomDialog = true },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Show Custom Dialog")
            }
        }
    }

    // A. STANDARD MATERIAL 3 ALERT DIALOG
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Alert Icon"
                )
            },
            title = {
                Text(text = "Confirm Deletion")
            },
            text = {
                Text(text = "Are you sure you want to delete this lecture note? This action cannot be undone.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showAlertDialog = false
                        Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showAlertDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    // B. CUSTOM LAYOUT DIALOG
    if (showCustomDialog) {
        Dialog(onDismissRequest = { showCustomDialog = false }) {
            Surface(
                shape = RoundedCornerShape(28.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "New Project",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        label = { Text("Enter Project Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showCustomDialog = false }) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                showCustomDialog = false
                                Toast.makeText(
                                    context,
                                    "Project Created: $inputText",
                                    Toast.LENGTH_SHORT
                                ).show()
                                inputText = "" // Reset state
                            }
                        ) {
                            Text("Submit")
                        }
                    }
                }
            }
        }
    }
}



// 3. PREVIEW
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DialogDemoScreenPreview() {
    MaterialTheme {
        DialogDemoScreen()
    }
}