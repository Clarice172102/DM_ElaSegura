package com.example.elasegura.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // localização atual simulada
    var currentAddress = mutableStateOf("Endereço não disponível")
        private set

    fun updateLocation() {
        // lógica real para atualizar a localização (exemplo simples)
        currentAddress.value = "Av. Prof. Luiz Freire 500, Recife, PE"
    }

    // lista de contatos
    // Guarda contatos em lista
    private val _contacts = mutableStateListOf<Contact>()
    val contacts: List<Contact> get() = _contacts

    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }
}