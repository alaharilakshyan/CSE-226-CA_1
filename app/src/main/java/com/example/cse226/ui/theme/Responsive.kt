package com.example.cse226.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// 1. ACTIVITY HOST (Android Entry Point)
class Responsive: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ResponsiveScreen()
                }
            }
        }
    }
}

// 2. ADAPTIVE COMPOSABLE (Local Constraints)
@Composable
fun ResponsiveScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // BoxWithConstraints exposes maxWidth and maxHeight of the parent layout scope
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            // Check if available width is less than 480.dp (Compact Width Threshold)
            if (maxWidth < 480.dp) {
                // Compact Layout: Vertically Stacked (Portrait / Mobile)
                CompactLayout()
            } else {
                // Expanded Layout: Side-by-Side (Landscape / Tablet)
                ExpandedLayout()
            }
        }
    }
}

// Sub-layout 1: Compact View
@Composable
private fun CompactLayout() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAvatar(modifier = Modifier.height(120.dp).fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        ProfileDetails()
    }
}

// Sub-layout 2: Expanded View
@Composable
private fun ExpandedLayout() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileAvatar(modifier = Modifier.width(160.dp).height(120.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier.weight(1f)) {
            ProfileDetails()
        }
    }
}

// Helper Visual Components
@Composable
private fun ProfileAvatar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF6200EE)),
        contentAlignment = Alignment.Center
    ) {
        Text("Avatar", color = Color.White, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
private fun ProfileDetails() {
    Column {
        Text("Arsalan", style = MaterialTheme.typography.headlineSmall)
        Text("Assistant Professor, CSE", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Demonstrates dynamic responsive UI switching using BoxWithConstraints in Jetpack Compose.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

// 3. PREVIEW FUNCTIONS (Testing Multiple Breakpoints)
@Preview(name = "Portrait - Compact", widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun ResponsiveProfileCardCompactPreview() {
    MaterialTheme { ResponsiveScreen() }
}

@Preview(name = "Landscape - Expanded", widthDp = 720, heightDp = 400, showBackground = true)
@Composable
fun ResponsiveProfileCardExpandedPreview() {
    MaterialTheme { ResponsiveScreen() }
}