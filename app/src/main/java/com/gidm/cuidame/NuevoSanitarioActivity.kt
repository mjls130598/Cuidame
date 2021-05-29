package com.gidm.cuidame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gidm.cuidame.adapter.RecyclerAdapter
import com.gidm.cuidame.adapter.Sanitario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NuevoSanitarioActivity : AppCompatActivity() {

    // Modo de visualización de la lista
    private lateinit var linearLayoutManager: LinearLayoutManager

    // El adaptador de la lista
    private lateinit var adapter: RecyclerAdapter

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_sanitario)

        // Elementos de la vista
        recyclerView = findViewById<RecyclerView>(R.id.sanitarios)
    }

    override fun onStart() {
        super.onStart()
        actualizarLista()
    }

    fun actualizarLista(){
        // Se indica el modo de organización de la lista
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val dbUsuario = FirebaseDatabase.getInstance().reference.child("Usuarios")

        val shared = getSharedPreferences("datos-paciente", MODE_PRIVATE)
        // Obtenemos el id del usuario
        val id = shared.getString("id", "")

        // Se obtienen los contactos guardados
        dbUsuario.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val sanitarios = ArrayList<Sanitario>()
                val usuarioActual = snapshot.child(id!!)

                val sanitariosUsuario = usuarioActual.child("sanitarios").value as ArrayList<*>

                Toast.makeText(this@NuevoSanitarioActivity, sanitariosUsuario.toString(), Toast.LENGTH_LONG).show()

                for(usuario in snapshot.children){
                    if(usuario.hasChild("especialidad") and
                            !sanitariosUsuario.contains(usuario.key as String)){
                        sanitarios.add(Sanitario(usuario.child("nombre").value as String,
                        usuario.key as String, usuario.child("especialidad").value as String))
                    }
                }

                adapter = RecyclerAdapter(sanitarios)
                recyclerView.adapter =adapter
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }
}