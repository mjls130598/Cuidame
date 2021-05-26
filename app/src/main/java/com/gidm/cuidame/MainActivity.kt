package com.gidm.cuidame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

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
        val perfil = findViewById<ImageView>(R.id.sanitarios)

        // Si clickea sobre "Account Circle"
        perfil.setOnClickListener {
            cambiarActividad(PerfilActivity::class.java)
        }
    }

    private fun cambiarActividad(clase : Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}