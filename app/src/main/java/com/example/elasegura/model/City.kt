package com.example.elasegura.model

import com.google.android.gms.maps.model.LatLng

data class City(
    val name: String,
    val elasegura: String? = null,
    val location: LatLng? = null
)
