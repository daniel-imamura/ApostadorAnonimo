package com.danielimamura.apostadoranonimo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.danielimamura.apostadoranonimo.ui.navigation.AppNavigation
import com.danielimamura.apostadoranonimo.ui.theme.ApostadorAnonimoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApostadorAnonimoTheme {
                AppNavigation()
            }
        }
    }
}