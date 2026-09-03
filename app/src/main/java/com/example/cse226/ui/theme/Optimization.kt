package com.example.cse226.ui.theme


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// 1. ACTIVITY HOST (Android Entry Point)

class Optimization: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    OptimizationScreen()
                }
            }
        }
    }
}


// 2. CORE OPTIMIZED COMPOSABLE
@Composable
fun OptimizationScreen() {
    val listState = rememberLazyListState()

    // OPTIMIZATION: derivedStateOf buffers frequent scroll offset state reads
    val showScrollToTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 5
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Heavy List Layout
        LazyColumn(state = listState) {
            items(100) { index ->
                Text(
                    text = "Item #$index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

        // Conditional UI driven strictly by the buffered boolean state
        AnimatedVisibility(
            visible = showScrollToTop,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { /* Scroll logic */ },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Scroll to Top")
            }
        }
    }
}

// ==========================================
// 3. PREVIEW FUNCTION
// ==========================================
@Preview(showBackground = true)
@Composable
fun PerformanceOptimizationScreenPreview() {
    MaterialTheme {
        OptimizationScreen()
    }
}