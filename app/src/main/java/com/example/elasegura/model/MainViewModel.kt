package com.example.elasegura.model

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.elasegura.db.fb.FBContact
import com.example.elasegura.db.fb.FBDatabase
import com.example.elasegura.db.fb.FBUser
import com.example.elasegura.db.fb.toFBContact
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.elasegura.monitor.EmergencyWorker

class MainViewModel(
    application: Application,
    private val db: FBDatabase
) : AndroidViewModel(application), FBDatabase.Listener {

    // Firebase
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    // Localização
    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    var currentLatLng = mutableStateOf<LatLng?>(null)
        private set

    var currentAddress = mutableStateOf("Obtendo localização...")
        private set

    // Contatos
    private val _contacts = mutableStateListOf<Contact>()
    val contacts: List<Contact> get() = _contacts

    // Usuário
    private val _user = mutableStateOf<User?>(null)
    val user: User? get() = _user.value

    init {
        db.setListener(this)
    }

    // =========================
    // Firebase Listener
    // =========================

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

    // =========================
    // Contatos
    // =========================

    fun addContact(contact: Contact) {
        db.add(contact.toFBContact())
    }

    fun removeContact(contact: Contact) {
        db.remove(contact.toFBContact())
    }

    // =========================
    // Localização
    // =========================

    @SuppressLint("MissingPermission")
    fun updateLocation(hasPermission: Boolean) {
        if (!hasPermission) {
            currentAddress.value = "Permissão de localização não concedida"
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {

                    currentLatLng.value =
                        LatLng(location.latitude, location.longitude)

                    val geocoder =
                        Geocoder(getApplication(), Locale.getDefault())

                    val addresses =
                        geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )

                    currentAddress.value =
                        addresses?.firstOrNull()?.getAddressLine(0)
                            ?: "Localização desconhecida"

                    saveLocationToFirestore()

                } else {
                    currentAddress.value = "Localização indisponível"
                }
            }
            .addOnFailureListener { e ->
                currentAddress.value =
                    "Erro ao obter localização: ${e.message}"
            }
    }

    private fun saveLocationToFirestore() {

        val userId = auth.currentUser?.uid
        val latLng = currentLatLng.value

        if (userId == null || latLng == null) return

        val locationData = hashMapOf(
            "timestamp" to FieldValue.serverTimestamp(),
            "latitude" to latLng.latitude,
            "longitude" to latLng.longitude
        )

        firestore.collection("user_locations")
            .document(userId)
            .collection("locations")
            .add(locationData)
    }

    // =========================
    // Mensagens de Emergência
    // =========================

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
        val latLng = currentLatLng.value ?: return null

        return """
            ⚠️ Estou me sentindo ameaçada!
            
            📍 Minha localização:
            https://maps.google.com/?q=${latLng.latitude},${latLng.longitude}
        """.trimIndent()
    }

    fun getImFineMessage(): String? {
        val latLng = currentLatLng.value ?: return null

        return """
            ✅ Estou bem!
            
            📍 Minha localização:
            https://maps.google.com/?q=${latLng.latitude},${latLng.longitude}
        """.trimIndent()
    }

    fun triggerEmergencyNotification(buttonType: String) {

        val data = workDataOf(
            EmergencyWorker.KEY_BUTTON_TYPE to buttonType
        )

        val workRequest = OneTimeWorkRequestBuilder<EmergencyWorker>()
            .setInputData(data)
            .build()

        WorkManager
            .getInstance(getApplication())
            .enqueue(workRequest)
    }
}

class MainViewModelFactory(
    private val application: Application,
    private val db: FBDatabase
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}