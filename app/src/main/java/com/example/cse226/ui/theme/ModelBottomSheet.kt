package com.example.cse226.ui.theme

import com.example.cse226.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// Compose Layouts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

// Material 3 Components
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ModalBottomSheet: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Makes app draw behind status bar
        setContent {
            PlaceScreen() // Calling our composable screen
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceScreen() {
    // Controls the state (Expanded / Hidden) of Modal Bottom Sheet
    val sheetState = rememberModalBottomSheetState()

    // Boolean variable to decide whether Bottom Sheet should appear
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        // Top App Bar
        topBar = {
            TopAppBar(
                title = {
                    Text("LPU Place Finder")
                }
            )
        }

    ) { padding ->

        Box( // Main container
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background( // Background Gradient
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color.White
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Card( // Card containing the place image
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lpu),
                        contentDescription = "LPU Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Text( // Heading
                    text = "Explore Nearby Places",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text( // Sub Heading
                    text = "Tap below to view place details",
                    color = Color.Gray,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(25.dp))


                Button( // Button to open Bottom Sheet
                    onClick = {
                        showBottomSheet = true // Make Bottom Sheet visible
                    }
                ) {
                    Text("Show Place Details")
                }
            }

            if (showBottomSheet) { // Bottom Sheet appears only if this condition becomes true

                ModalBottomSheet(
                    // Executed when user taps outside or swipes down the Bottom Sheet
                    onDismissRequest = {
                        showBottomSheet = false // Hide Bottom Sheet
                    },
                    sheetState = sheetState // Bottom Sheet State

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {

                        Text( // Place Name
                            text = "📍 Lovely Professional University",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text( // Rating
                            text = "⭐ Rating : 4.4 (25K Reviews)",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text( // Location
                            text = "📍 Phagwara, Punjab",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Row( // Action Buttons
                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { }) {
                                Text("Directions")
                            }

                            Button(onClick = { }) {
                                Text("Call")
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ModalBottomSheetScreenPreview() {
    // Apply your app theme if you have one, or call the composable directly
    PlaceScreen()
}