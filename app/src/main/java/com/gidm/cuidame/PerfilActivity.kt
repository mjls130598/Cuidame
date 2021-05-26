package com.gidm.cuidame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Recogemos los elementos de la pantalla
        val nombre = findViewById<TextView>(R.id.nombre)
        val email = findViewById<TextView>(R.id.email)

        // Obtenemos el id del usuario
        val shared = getSharedPreferences("datos-paciente", MODE_PRIVATE)
        val id = shared.getString("id", null)

        // Creamos instancia con la base de datos encargada de las autorizaciones
        val auth = FirebaseAuth.getInstance()


        if (id != null){

            val usuario = auth.currentUser

            // Accedemos a los datos del usuario
            val datosUsuario = FirebaseDatabase.getInstance().
            getReference("Usuarios").child(id).child("nombre")


            if(usuario != null){
                datosUsuario.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        nombre.text = dataSnapshot.getValue(String::class.java)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
                email.text = usuario.email
            }
        }
    }
}