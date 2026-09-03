package com.example.cse226.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class ComposeAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationScreen()
        }
    }
}

@Composable
fun AnimationScreen() {
    var isExpanded by remember {mutableStateOf(false)}

    val scale by animateFloatAsState(
        targetValue = if(isExpanded) 1.5f else 1.0f,
        animationSpec = tween(durationMillis = 4000),
        label = "scaleAnimation"
    )

    val opacity by animateFloatAsState(
        targetValue = if (isExpanded) 1.0f else 0.5f,
        animationSpec = tween(durationMillis = 4000),
        label = "alphaAnimation"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box (
            modifier = Modifier
                .size ((100*scale).dp)
                .alpha(opacity)
                .background(Color(0xFF6200EE))
                .clickable {isExpanded = !isExpanded},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if(isExpanded) "Shrink" else "Expand",
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimationPreview() {
    ComposeAnimation()
}