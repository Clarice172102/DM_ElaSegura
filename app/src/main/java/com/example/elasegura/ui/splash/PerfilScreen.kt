package com.example.elasegura.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.elasegura.R
import com.example.elasegura.ui.navigation.Route
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PerfilScreen(navController: NavHostController) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF3DDFB),
            Color(0xFFEAD3F7),
            Color(0xFFF0DAF8)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Voltar",
                    tint = Color(0xFF4A148C),
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.notifications),
                    contentDescription = "Notificações",
                    tint = Color(0xFF4A148C),
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        MenuCard(
            iconRes = R.drawable.user,
            text = "Conta",
            onClick = {  }
        )

        Spacer(modifier = Modifier.height(20.dp))

        MenuCard(
            iconRes = R.drawable.message_square,
            text = "Compartilhar Localização para a comunidade",
            onClick = { }
        )

        Spacer(modifier = Modifier.height(20.dp))

        MenuCard(
            iconRes = R.drawable.smartphone,
            text = "Dispositivos",
            onClick = { }
        )

        Spacer(modifier = Modifier.height(20.dp))

        MenuCard(
            iconRes = R.drawable.settings,
            text = "Configurações",
            onClick = { }
        )

        Spacer(modifier = Modifier.height(20.dp))

        MenuCard(
            iconRes = R.drawable.log_out,
            text = "Sair",
            {
                FirebaseAuth.getInstance().signOut()
                navController.navigate(Route.Login.route) {
                    popUpTo(Route.Home.route) { inclusive = true } // remove Home da pilha
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Todos os direitos reservados",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            fontSize = 14.sp,
            color = Color(0xFF4A148C),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun MenuCard(iconRes: Int, text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD8B8F8)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color(0xFF4A148C),
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, fontSize = 18.sp, color = Color.Black)
        }
    }
}