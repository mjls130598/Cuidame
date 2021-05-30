package com.gidm.cuidame

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Miramos si hay algún id en la memoria del dispositivo
        val shared = getSharedPreferences("datos-paciente", MODE_PRIVATE) ?: return
        val id = shared.getString("id", null)

        // Sino lo hay, ...
        if(id == null){
            // Nos dirigimos a la actividad de iniciar sesión
            cambiarActividad(IniciarSesionActivity::class.java)
            finish()
        }

        // Obtenemos los distintos botones del menú
        val perfil = findViewById<LinearLayout>(R.id.perfil)
        val logout = findViewById<LinearLayout>(R.id.logout)
        val nuevoSanitario = findViewById<LinearLayout>(R.id.añadirSanitarios)
        val misSanitarios = findViewById<LinearLayout>(R.id.sanitarios)
        val chats = findViewById<LinearLayout>(R.id.chats)

        // Si clickea sobre "Perfil"
        perfil.setOnClickListener {
            cambiarActividad(PerfilActivity::class.java)
        }

        // Si clickea sobre "Salir"
        logout.setOnClickListener{

            // Cierra la sesión en Firebase
            FirebaseAuth.getInstance().signOut()

            // Se elimina los datos de la memoria local
            with(shared.edit()){
                remove("id")
                commit()
            }

            cambiarActividad(IniciarSesionActivity::class.java)
            finish()
        }

        // Si clickea sobre "Nuevo sanitario"
        nuevoSanitario.setOnClickListener {
            cambiarActividad(NuevoSanitarioActivity::class.java)
        }

        // Si se pulsa sobre "Mis sanitarios"
        misSanitarios.setOnClickListener {
            cambiarActividad(MisSanitariosActivity::class.java)
        }

        // Si pulsa sobre "Chats"
        chats.setOnClickListener {
            cambiarActividad(ChatsActivity::class.java)
        }
    }

    private fun cambiarActividad(clase : Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}