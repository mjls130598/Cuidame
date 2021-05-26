package com.gidm.cuidame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class IniciarSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        // Elementos de la pantalla
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputContrasenia = findViewById<EditText>(R.id.inputContraseña)
        val iniciarSesion = findViewById<Button>(R.id.iniciar_sesion)
        val registrarse = findViewById<Button>(R.id.registrarse)

        // Si le da al botón "Iniciar sesión", ...
        iniciarSesion.setOnClickListener{

            // Recoge los datos de los campos de la pantalla
            val email = inputEmail.text.toString()
            val contrasenia = inputContrasenia.text.toString()


        }

        // Si le da al botón "Registrarse", ...
        registrarse.setOnClickListener{

            // Se va a la Activity RegistrarseActivity
            val intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(intent)
        }
    }
}