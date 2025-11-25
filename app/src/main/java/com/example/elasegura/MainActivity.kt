package com.example.elasegura

import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.navigation.compose.rememberNavController
import com.example.elasegura.ui.navigation.MainScreen
import com.example.elasegura.ui.navigation.NavGraph
import com.example.elasegura.ui.theme.ElaSeguraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
            ElaSeguraTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}