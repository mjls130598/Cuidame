package com.gidm.cuidame.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.gidm.cuidame.R
import com.google.firebase.database.FirebaseDatabase

class RecyclerAdapter(private val sanitarios: ArrayList<Sanitario>):
    RecyclerView.Adapter<RecyclerAdapter.SanitarioHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SanitarioHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_sanitarios, false)
        return SanitarioHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SanitarioHolder, position: Int) {
        val itemContacto = sanitarios[position]
        holder.bindContacto(itemContacto)
    }

    override fun getItemCount(): Int = sanitarios.size

    class SanitarioHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        private var sanitario: Sanitario? = null
        private var view: View = v

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val context = p0!!.context
            val idSanitario = sanitario!!.uid

            // Añadimos al nuevo sanitario en la base de datos
            val shared = context.getSharedPreferences("datos-paciente", AppCompatActivity.MODE_PRIVATE)
            val idUsuario = shared.getString("id", "")

            val dbUsuario = FirebaseDatabase.getInstance().reference.child("Usuarios").child(idUsuario!!)

            dbUsuario.child("sanitarios").setValue(idSanitario).addOnCompleteListener {
                if(it.isSuccessful)
                // Informamos al usuario
                    Toast.makeText(context, "Se ha añadido ${sanitario!!.nombre}", Toast.LENGTH_LONG).show()
            }
        }

        fun bindContacto(sanitario: Sanitario) {
            this.sanitario = sanitario
            val nombre = view.findViewById<TextView>(R.id.nombreSanitario)
            val especialidad = view.findViewById<TextView>(R.id.especialidadSanitario)
            nombre.text = sanitario.nombre
            especialidad.text = sanitario.especialidad
        }


        companion object {
            private val SANITARIO_KEY = "SANITARIO"
        }

    }
}