package com.example.elasegura.db.fb

import com.example.elasegura.model.Contact

class FBContact {
    var name: String? = null
    var phone: String? = null
    var vinculo: String? = null

    fun toContact(): Contact {
        return Contact(
            name = name!!,
            phone = phone!!,
            vinculo = vinculo!!
        )
    }
}

fun Contact.toFBContact(): FBContact {
    val fb = FBContact()
    fb.name = this.name
    fb.phone = this.phone
    fb.vinculo = this.vinculo
    return fb
}