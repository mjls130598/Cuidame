package com.gidm.cuidame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegistrarseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_usuario)

        // Obtenemos los elementos de la vista
        val nombreInput = findViewById<EditText>(R.id.inputNombre)
        val correoInput = findViewById<EditText>(R.id.inputNuevoEmail)
        val contraseniaInput = findViewById<EditText>(R.id.inputNewContrasenia)
        val contraseniaRepInput = findViewById<EditText>(R.id.inputNewContrasenia2)
        val guardar = findViewById<Button>(R.id.nuevoUsuario)

        // Si se pulsa sobre "Guardar", ...
        guardar.setOnClickListener{

            val nombre = nombreInput.text.toString()
            val correo = correoInput.text.toString()
            val contrasenia = contraseniaInput.text.toString()
            val contraseniaRep =  contraseniaRepInput.text.toString()

            // Si los datos introducidos son correctos, ...
            if(Utils.comprobarUsuario(nombre, correo, contrasenia, contraseniaRep, this)){
            }
        }
    }
}