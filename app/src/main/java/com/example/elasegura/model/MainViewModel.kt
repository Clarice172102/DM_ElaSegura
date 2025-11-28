package com.example.elasegura.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MainViewModel : ViewModel() {
    // cidades já existentes
    private val _cities = getCitiesList().toMutableStateList()
    val cities get() = _cities.toList()

    // localização atual simulada
    var currentAddress = mutableStateOf("Endereço não disponível")
        private set

    fun updateLocation() {
        // lógica real para atualizar a localização (exemplo simples)
        currentAddress.value = "Av. Prof. Luiz Freire 500, Recife, PE"
    }

    // lista de contatos
    private val _contacts = mutableStateListOf<Contact>()
    val contacts: List<Contact> get() = _contacts

    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }

    private fun getCitiesList() = List(20) { i ->
        City(name = "Cidade $i", elasegura = "Carregando clima...")
    }
}