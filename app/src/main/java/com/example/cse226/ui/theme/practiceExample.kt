package com.example.cse226.ui.theme

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MaterialAnimationApp() {

    var expanded by remember {
        mutableStateOf(false)
    }

    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 250.dp else 120.dp
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Material 3 Animation"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Android Development")

                if (expanded) {
                    Text("This is additional information.")
                }
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {
                expanded = !expanded
            }
        ) {

            Text(
                if (expanded)
                    "Collapse"
                else
                    "Expand"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaterialAnimationAppPreview() {
    CSE226Theme {
        MaterialAnimationApp()
    }
}