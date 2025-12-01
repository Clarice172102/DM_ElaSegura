package com.example.elasegura.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun ContactsScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    onAddContact: () -> Unit = { navController.navigate("addContact") }

) {
    val contacts = viewModel.contacts

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
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ⭐ Botões de abas: Meus Contatos | Adicionar
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /* já está nessa tela */ },
                colors = ButtonDefaults.buttonColors(Color(0xFFAA71D0)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Meus Contatos", color = Color.White)
            }

            Button(
                onClick = onAddContact,
                colors = ButtonDefaults.buttonColors(Color(0xFFD8B8F8)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Adicionar", color = Color(0xFF4A148C))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Contatos", color = Color(0xFF4A148C), fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Aqui estão suas pessoas de confiança:", color = Color(0xFF4A148C))

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(contacts) { contact ->
                ContactCard(contact = contact, onClick = {})
            }
        }
    }
}

@Composable
fun ContactCard(contact: Contact, onClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFD8B8F8))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                tint = Color(0xFF4A148C)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = contact.name,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A148C)
                )
                Text(
                    text = contact.phone,
                    color = Color(0xFF4A148C)
                )
            }
        }
    }
}