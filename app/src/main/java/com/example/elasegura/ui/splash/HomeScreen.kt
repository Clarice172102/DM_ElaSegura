package com.example.elasegura.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.elasegura.R
import com.example.elasegura.model.MainViewModel
import com.example.elasegura.ui.navigation.BottomNavItem
import com.example.elasegura.ui.navigation.Route
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    bottomNavItems: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onPrecisoAjuda: () -> Unit = {},
    onAmeacada: () -> Unit = {},
    onEstouBem: () -> Unit = {}
) {
    val context = LocalContext.current

    val workManager = androidx.work.WorkManager.getInstance(context)

    fun triggerNotification(buttonType: String) {

        val data = androidx.work.workDataOf(
            com.example.elasegura.monitor.EmergencyWorker.KEY_BUTTON_TYPE to buttonType
        )

        val request =
            androidx.work.OneTimeWorkRequestBuilder<
                    com.example.elasegura.monitor.EmergencyWorker>()
                .setInputData(data)
                .build()

        workManager.enqueue(request)
    }

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasLocationPermission = granted
            if (granted) {
                viewModel.updateLocation(hasLocationPermission)
            }
        }

    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            permissionLauncher.launch(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            viewModel.updateLocation(hasLocationPermission)
        }
    }

    // Gradiente de fundo
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFCAB0EE),  // Roxo claro
            Color(0xFFCAB0EE),  // Roxo claro
            Color(0xFFCAB0EE),
            Color(0xFFF3E5F5),  // Branco
            Color(0xFFF3E5F5),
            Color(0xFFF3E5F5)
        ),
        startY = 0f,
        endY = 1200f
    )


    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFD8B8F8),
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(Route.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = item.title,
                                tint = Color(0xFF6A1B9A)
                            )
                        },
                        label = {
                            Text(item.title, color = Color.Black)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = Color(0xFF6A1B9A),
                            selectedTextColor = Color.Black,
                            unselectedIconColor = Color(0xFF6A1B9A),
                            unselectedTextColor = Color.Black
                        )
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .background(gradient)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_elasegura1),
                    contentDescription = "Logo",
                    modifier = Modifier.size(90.dp)
                )


                IconButton(
                    onClick = { navController.navigate(Route.Perfil.route) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "Perfil",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF4A148C)
                    )
                }
            }


            Text(
                text = "Bem-vinda, ${viewModel.user?.name ?: "..." }",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A148C)
            )


            Spacer(modifier = Modifier.height(120.dp))


            Button(
                onClick = {
                    triggerNotification("HELP")

                    val message = viewModel.getEmergencyMessage()

                    if (message == null) {
                        Toast.makeText(
                            context,
                            "Localização ainda não disponível",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        openWhatsAppChooser(context, message)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sirene_1),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Preciso de Ajuda", fontSize = 18.sp, color = Color.Black)
            }


            Spacer(modifier = Modifier.height(30.dp))


            Button(
                onClick = {
                    triggerNotification("DANGER")

                    val message = viewModel.getThreatenedMessage()

                    if (message == null) {
                        Toast.makeText(
                            context,
                            "Localização ainda não disponível",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        openWhatsAppChooser(context, message)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alert_triangle),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Me sinto Ameaçada", fontSize = 18.sp, color = Color.Black)
            }


            Spacer(modifier = Modifier.height(30.dp))


            Button(
                onClick = {
                    triggerNotification("FINE")

                    val message = viewModel.getImFineMessage()

                    if (message == null) {
                        Toast.makeText(
                            context,
                            "Localização ainda não disponível",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        openWhatsAppChooser(context, message)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Estou Bem", fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}


fun openWhatsAppChooser(
    context: android.content.Context,
    message: String
) {
    //Intent genérica para compartilhar texto
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }


    //Mostrar todos apps que podem receber texto (WhatsApp normal e Business, Telegram etc)
    val chooser = Intent.createChooser(sendIntent, "Compartilhar via")


    try {
        context.startActivity(chooser)
    } catch (e: Exception) {
        Toast.makeText(context, "Nenhum aplicativo disponível", Toast.LENGTH_SHORT).show()
    }
}