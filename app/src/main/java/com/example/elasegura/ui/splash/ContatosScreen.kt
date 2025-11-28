package com.example.elasegura.ui.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elasegura.model.Contact

@Composable
fun ContactsScreen(
    navController: NavHostController,
    contacts: List<Contact>,
    onAddContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddContact, containerColor = Color(0xFFD8B8F8)) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Contato", tint = Color(0xFF4A148C))
            }
        },
        containerColor = Color(0xFFF3E5F5)
    ) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF3E5F5))
                .padding(16.dp)
        ) {
            Text(
                "Meus Contatos",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF4A148C)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(contacts) { contact ->
                    ContactItem(contact)
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 4.dp,
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFD8B8F8))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(contact.name, color = Color(0xFF4A148C))
            Text(contact.phone, color = Color(0xFF4A148C))
        }
    }
}