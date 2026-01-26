package com.example.elasegura.db.fb

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore

class FBDatabase {

    interface Listener {
        fun onUserLoaded(user: FBUser)
        fun onUserSignOut()

        fun onContactAdded(contact: FBContact)
        fun onContactRemoved(contact: FBContact)
    }

    private val auth = Firebase.auth
    private val db = Firebase.firestore

    private var contactsReg: ListenerRegistration? = null
    private var listener: Listener? = null

    init {
        auth.addAuthStateListener { auth ->
            if (auth.currentUser == null) {
                contactsReg?.remove()
                listener?.onUserSignOut()
                return@addAuthStateListener
            }

            val refUser = db.collection("users")
                .document(auth.currentUser!!.uid)

            refUser.get().addOnSuccessListener {
                it.toObject(FBUser::class.java)?.let { user ->
                    listener?.onUserLoaded(user)
                }
            }

            contactsReg = refUser.collection("contacts")
                .addSnapshotListener { snapshots, _ ->
                    snapshots?.documentChanges?.forEach { change ->
                        val fbContact =
                            change.document.toObject(FBContact::class.java)

                        if (change.type == DocumentChange.Type.ADDED) {
                            listener?.onContactAdded(fbContact)
                        } else if (change.type == DocumentChange.Type.REMOVED) {
                            listener?.onContactRemoved(fbContact)
                        }
                    }
                }
        }
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    fun register(user: FBUser) {
        val uid = auth.currentUser?.uid
            ?: throw RuntimeException("User not logged in")

        db.collection("users").document(uid).set(user)
    }

    fun add(contact: FBContact) {
        val uid = auth.currentUser?.uid
            ?: throw RuntimeException("User not logged in")

        db.collection("users")
            .document(uid)
            .collection("contacts")
            .document(contact.name!!)
            .set(contact)
    }

    fun remove(contact: FBContact) {
        val uid = auth.currentUser?.uid
            ?: throw RuntimeException("User not logged in")

        db.collection("users")
            .document(uid)
            .collection("contacts")
            .document(contact.name!!)
            .delete()
    }

}