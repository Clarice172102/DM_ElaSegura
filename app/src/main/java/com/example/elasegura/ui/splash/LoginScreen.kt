package com.example.elasegura.ui.splash

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elasegura.R
import com.example.elasegura.ui.navigation.Route
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    navController: NavController,
    onHelpClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFC7A4F2), Color.White)
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Logo e nome
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFC7A4F2), Color.Transparent)
                    )
                )
                .padding(vertical = 32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo_elasegura1), // seu logo aqui
                contentDescription = "ElaSegura",
                tint = Color(0xFF6C3EA9),
                modifier = Modifier.size(64.dp)
            )
            Text(
                "ElaSegura",
                color = Color(0xFF6C3EA9),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Caixa lilás
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD7B6F9), shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
                .shadow(5.dp, RoundedCornerShape(16.dp))
        ) {
            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color(0xFF6C3EA9)) },
                placeholder = { Text("Ex: ElaSegura@gmail.com") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = null,
                        tint = Color(0xFF6C3EA9)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6C3EA9),
                    unfocusedBorderColor = Color(0xFF6C3EA9),
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Senha
            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha", color = Color(0xFF6C3EA9)) },
                placeholder = { Text(".......") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = null,
                        tint = Color(0xFF6C3EA9)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6C3EA9),
                    unfocusedBorderColor = Color(0xFF6C3EA9),
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Esqueci a minha senha",
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { /* ação recuperar senha */ },
                color = Color(0xFF6C3EA9),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Login OK!", Toast.LENGTH_LONG).show()
                                navController.navigate(Route.Home.route) {
                                    popUpTo(Route.Login.route) { inclusive = true }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Login FALHOU!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color(0xFF6C3EA9))
            ) {
                Text("Entrar", color = Color(0xFF6C3EA9))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Não tem uma conta? ", color = Color(0xFF6C3EA9))
                Text(
                    "Criar conta",
                    color = Color(0xFF6C3EA9),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onCreateAccountClick() }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "Precisa de Ajuda? Clique aqui",
            color = Color(0xFF6C3EA9),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable { onHelpClick() }
        )
    }
}