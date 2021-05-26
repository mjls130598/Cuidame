package com.gidm.cuidame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IniciarSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val registrarse = findViewById<Button>(R.id.registrarse)

        // Si le da al botón "Registrarse", ...
        registrarse.setOnClickListener{

            // Se va a la Activity RegistrarseActivity
            val intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(intent)
        }
    }
}