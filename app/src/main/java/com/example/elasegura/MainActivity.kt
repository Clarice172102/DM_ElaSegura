package com.example.elasegura

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.elasegura.db.fb.FBDatabase
import com.example.elasegura.model.MainViewModel
import com.example.elasegura.model.MainViewModelFactory
import com.example.elasegura.ui.navigation.NavGraph
import com.example.elasegura.ui.theme.ElaSeguraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
        }
        enableEdgeToEdge()

        setContent {
            ElaSeguraTheme {
                val navController = rememberNavController()
                val fbDB = remember { FBDatabase() }

                val mainViewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(
                        application = application,
                        db = fbDB
                    )
                )
                NavGraph(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }
        }
    }
}