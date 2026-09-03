package com.example.cse226

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cse226.ui.theme.CSE226Theme
import com.example.cse226.ui.theme.SimpleBankingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSE226Theme {
                SimpleBankingScreen()
            }
        }
    }
}
