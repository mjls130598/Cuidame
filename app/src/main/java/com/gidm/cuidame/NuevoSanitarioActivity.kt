package com.gidm.cuidame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gidm.cuidame.adapter.NuevosSanitariosAdapter
import com.gidm.cuidame.adapter.Sanitario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NuevoSanitarioActivity : AppCompatActivity() {

    // Modo de visualización de la lista
    private lateinit var linearLayoutManager: LinearLayoutManager

    // El adaptador de la lista
    private lateinit var adapter: NuevosSanitariosAdapter

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sanitarios)

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

        // Se obtienen los sanitarios registrados
        dbUsuario.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val sanitarios = ArrayList<Sanitario>()
                val usuarioActual = snapshot.child(id!!)

                val sanitariosUsuario = if(usuarioActual.child("sanitarios").value != null){
                    usuarioActual.child("sanitarios").value as HashMap<String, String>
                } else HashMap<String, String>()

                for(usuario in snapshot.children){
                    if(usuario.hasChild("especialidad") and
                            !sanitariosUsuario.containsValue(usuario.key as String)){
                        sanitarios.add(Sanitario(usuario.child("nombre").value as String,
                        usuario.key as String, usuario.child("especialidad").value as String))
                    }
                }

                adapter = NuevosSanitariosAdapter(sanitarios)
                recyclerView.adapter =adapter
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }
}