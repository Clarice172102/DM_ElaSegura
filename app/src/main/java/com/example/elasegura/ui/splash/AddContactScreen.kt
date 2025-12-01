package com.example.elasegura.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elasegura.R
import com.example.elasegura.model.Contact
import com.example.elasegura.model.MainViewModel

@Composable
fun AddContactScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var vinculo by remember { mutableStateOf("Família") }

    val vinculos = listOf("Família", "Amigos", "Trabalho", "Outros")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .padding(16.dp)
    ) {

        // 🔙 Voltar
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Voltar",
                    tint = Color(0xFF4A148C)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ⭐ Botões superiores
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.navigate("contatos") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color(0xFFD8B8F8))
            ) {
                Text("Meus Contatos", color = Color(0xFF4A148C))
            }

            Button(
                onClick = { /* já está nessa tela */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color(0xFFAA71D0))
            ) {
                Text("Adicionar", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔤 Título
        Text(
            "Novo Contato",
            color = Color(0xFF4A148C),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Cadastre uma pessoa de confiança, é importante que a pessoa saiba que irá receber seus pedidos de socorro via WhatsApp",
            color = Color(0xFF4A148C),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🟣 Nome
        Text("Nome do Contato", color = Color(0xFF4A148C), fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Ex: Sofia Cavalcante") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    tint = Color(0xFF4A148C)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFAA71D0),
                unfocusedBorderColor = Color(0xFFAA71D0)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🟣 Número
        Text("Número do Contato", color = Color(0xFF4A148C), fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            placeholder = { Text("(00) 00000-0000") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.smartphone),
                    contentDescription = null,
                    tint = Color(0xFF4A148C)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFAA71D0),
                unfocusedBorderColor = Color(0xFFAA71D0)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🟣 Dropdown de vínculo
        Text("Vínculo", color = Color(0xFF4A148C), fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))

        Box {
            OutlinedTextField(
                value = vinculo,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                enabled = false,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null,
                        tint = Color(0xFF4A148C)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.chevron_left),
                        contentDescription = null
                    )
                },
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                vinculos.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            vinculo = it
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 🟠 BOTÃO LARANJA
        Button(
            onClick = {
                if (name.isNotEmpty() && phone.isNotEmpty()) {
                    viewModel.addContact(Contact(name, phone))
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFA726))
        ) {
            Text("Adicionar Contato", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}