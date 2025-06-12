package com.example.modul4.presentation

import android.os.Bundle
import com.example.modul4.ui.theme.presentation.DinoApp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.modul4.ui.theme.DinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinoTheme {
                DinoApp()
            }
        }
    }
}
