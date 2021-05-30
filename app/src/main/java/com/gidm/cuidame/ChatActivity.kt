package com.gidm.cuidame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val idSanitario = intent.getStringExtra("idSanitario")

        // Obtenemos el id del usuario actual
        val shared = getSharedPreferences("datos-paciente", MODE_PRIVATE)
        val idUsuario = shared.getString("id", "")

    }
}