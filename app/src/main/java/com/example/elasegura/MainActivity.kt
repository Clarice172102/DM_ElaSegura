package com.example.elasegura

import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.elasegura.db.fb.FBDatabase
import com.example.elasegura.model.MainViewModel
import com.example.elasegura.model.MainViewModelFactory
import com.example.elasegura.ui.navigation.NavGraph
import com.example.elasegura.ui.theme.ElaSeguraTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElaSeguraTheme {
                val navController = rememberNavController()

                val fbDB = remember { FBDatabase() }

                val mainViewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(application, fbDB)
                )

                NavGraph(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }
        }
    }
}