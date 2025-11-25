package com.example.elasegura.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elasegura.R

@Composable
fun HomeScreen(
    onPrecisoAjuda: () -> Unit = {},
    onAmeacada: () -> Unit = {},
    onEstouBem: () -> Unit = {}
) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFCAB0EE),  // Roxo claro no meio
            Color(0xFFCAB0EE),  // Roxo claro no meio
            Color(0xFFCAB0EE),  // Roxo claro no meio
            Color(0xFFEFD9FF),  // Roxo claro no meio
            Color(0xFFF3E5F5),   // Branco no final
            Color(0xFFF3E5F5)   // Branco no final
        ),
        startY = 0f,
        endY = 1200f   // aumenta ou diminui para controlar a transição
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        // LOGO
        Image(
            painter = painterResource(id = R.drawable.logo_elasegura1),
            contentDescription = "Logo ElaSegura",
            modifier = Modifier.size(170.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        // BOTÃO 1 — PRECISO DE AJUDA
        Button(
            onClick = onPrecisoAjuda,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Preciso de Ajuda", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // BOTÃO 2 — ME SINTO AMEAÇADA
        Button(
            onClick = onAmeacada,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Me sinto Ameaçada", fontSize = 18.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // BOTÃO 3 — ESTOU BEM
        Button(
            onClick = onEstouBem,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Estou Bem", fontSize = 18.sp, color = Color.White)
        }
    }
}