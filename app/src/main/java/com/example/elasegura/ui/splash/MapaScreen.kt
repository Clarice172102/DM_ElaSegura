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
    onShowPeopleAround: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Voltar",
                    tint = Color(0xFF4A148C)
                )
            }

            Text(
                "Você está em:",
                color = Color(0xFF4A148C),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = currentAddress,
            color = Color(0xFF4A148C),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onUpdateLocation,
                colors = ButtonDefaults.buttonColors(Color(0xFFAA71D0)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Atualizar Localização", color = Color.White)
            }

            Button(
                onClick = onShowPeopleAround,
                colors = ButtonDefaults.buttonColors(Color(0xFFD8B8F8)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Pessoas ao Redor", color = Color(0xFF4A148C))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {}
        }
    }
}
