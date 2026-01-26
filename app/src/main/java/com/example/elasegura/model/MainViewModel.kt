package com.example.elasegura.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elasegura.db.fb.FBContact
import com.example.elasegura.db.fb.FBDatabase
import com.example.elasegura.db.fb.FBUser
import com.example.elasegura.db.fb.toFBContact
import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import java.util.Locale
import com.google.firebase.firestore.FieldValue
import java.util.Date
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel (application: Application, private val db: FBDatabase): AndroidViewModel(application),
    FBDatabase.Listener {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    var currentLatLng = mutableStateOf<LatLng?>(null)
        private set

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    var currentAddress = mutableStateOf("Obtendo localização...")
        private set

    @SuppressLint("MissingPermission")
    fun updateLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    // 📍 Atualiza LatLng
                    currentLatLng.value = LatLng(location.latitude, location.longitude)

                    // 📍 Converte para endereço
                    val geocoder = Geocoder(getApplication(), Locale.getDefault())
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    currentAddress.value = addresses?.firstOrNull()?.getAddressLine(0) ?: "Localização desconhecida"

                    // 📍 Salva no Firestore
                    saveLocationToFirestore()
                } else {
                    currentAddress.value = "Localização indisponível"
                }
            }
            .addOnFailureListener { e ->
                currentAddress.value = "Erro ao obter localização: ${e.message}"
            }
    }

    fun getEmergencyMessage(): String? {
        val latLng = currentLatLng.value ?: return null

        val mapsLink =
            "https://maps.google.com/?q=${latLng.latitude},${latLng.longitude}"

        return """
        🚨 Pedido de ajuda!
        Estou em perigo.

        📍 Minha localização:
        $mapsLink
    """.trimIndent()
    }

    fun getThreatenedMessage(): String? {
        currentLatLng.value?.let {
            return "⚠️ Estou me sentindo ameaçada!\nMinha localização atual: https://maps.google.com/?q=${it.latitude},${it.longitude}"
        }
        return null
    }

    fun getImFineMessage(): String? {
        currentLatLng.value?.let {
            return "✅ Estou bem!\nMinha localização atual: https://maps.google.com/?q=${it.latitude},${it.longitude}"
        }
        return null
    }

    fun saveLocationToFirestore() {
        val userId = auth.currentUser?.uid
        val latLng = currentLatLng.value

        if (userId == null || latLng == null) return

        // Cria o registro com data/hora e lat/lng
        val locationData = hashMapOf(
            "timestamp" to FieldValue.serverTimestamp(), // data/hora do servidor
            "latitude" to latLng.latitude,
            "longitude" to latLng.longitude
        )

        // Salva dentro da coleção "user_locations" → cada usuário tem seus registros
        firestore.collection("user_locations")
            .document(userId)
            .collection("locations")
            .add(locationData)
            .addOnSuccessListener {
                println("Localização salva no Firestore com sucesso")
            }
            .addOnFailureListener { e ->
                println("Erro ao salvar localização: ${e.message}")
            }
    }

    // lista de contatos
    // Guarda contatos em lista
    private val _contacts = mutableStateListOf<Contact>()
    val contacts: List<Contact> get() = _contacts

    private val _user = mutableStateOf<User?>(null)
    val user: User? get() = _user.value

    init {
        db.setListener(this)
    }

    fun addContact(contact: Contact) {
        db.add(contact.toFBContact())
    }

    fun removeContact(contact: Contact) {
        db.remove(contact.toFBContact())
    }

    override fun onUserLoaded(user: FBUser) {
        _user.value = user.toUser()
    }

    override fun onUserSignOut() {
        _contacts.clear()
        _user.value = null
    }

    override fun onContactAdded(contact: FBContact) {
        _contacts.add(contact.toContact())
    }

    override fun onContactRemoved(contact: FBContact) {
        _contacts.remove(contact.toContact())
    }

}

class MainViewModelFactory(private val application: Application, private val db: FBDatabase ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}