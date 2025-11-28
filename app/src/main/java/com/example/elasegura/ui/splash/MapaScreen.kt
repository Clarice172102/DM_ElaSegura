package com.example.elasegura.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elasegura.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MapScreen(
    navController: NavHostController,
    currentAddress: String,
    onUpdateLocation: () -> Unit,
    onShowPeopleAround: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Voltar",
                    tint = Color(0xFF4A148C)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Você está em:",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF4A148C)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = currentAddress,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF4A148C)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onUpdateLocation,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAA71D0)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Atualizar Localização", color = Color.White)
            }

            Button(
                onClick = onShowPeopleAround,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD8B8F8)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Pessoas ao Redor", color = Color(0xFF4A148C))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Aqui vai o mapa, exemplo simulando com Box cinza
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.LightGray)
        ) {
            // Aqui você pode colocar o Google Map Compose
            // MapView ou GoogleMap composable (exemplo abaixo)

            /*
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                // outros parâmetros
            ) {
                Marker(
                    position = LatLng(...),
                    title = "Você está aqui"
                )
            }
            */
        }
    }
}