package com.example.elasegura.ui.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elasegura.R

@Composable
fun RegisterScreen(
    onCreateAccount: () -> Unit,
    onLoginClick: () -> Unit
) {
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

        // Ícone voltar
        IconButton(onClick = { /* ação voltar */ }, modifier = Modifier.align(Alignment.Start)) {
            Icon(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = "Voltar",
                tint = Color(0xFF6C3EA9)
            )
        }

        Text(
            "Criar Conta",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF6C3EA9),
            modifier = Modifier.padding(vertical = 12.dp)
        )

        // Caixa lilás
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD7B6F9), shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
                .shadow(5.dp, RoundedCornerShape(16.dp))
        ) {
            // Nome Completo
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Nome Completo", color = Color(0xFF6C3EA9)) },
                placeholder = { Text("Ex: Lili Silva") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
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

            // CPF
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("CPF", color = Color(0xFF6C3EA9)) },
                placeholder = { Text("123.321.213-32") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.file_text),
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

            // Celular
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Celular", color = Color(0xFF6C3EA9)) },
                placeholder = { Text("(00) 00000-0000") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.phone),
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

            // Email
            OutlinedTextField(
                value = "",
                onValueChange = {},
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

            Spacer(modifier = Modifier.height(12.dp))

            // Senha
            OutlinedTextField(
                value = "",
                onValueChange = {},
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
                shape = RoundedCornerShape(50),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onCreateAccount,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color(0xFF6C3EA9))
            ) {
                Text("Criar Conta", color = Color(0xFF6C3EA9))
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onLoginClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("já tem uma conta? Entrar", color = Color(0xFF6C3EA9))
            }
        }
    }
}