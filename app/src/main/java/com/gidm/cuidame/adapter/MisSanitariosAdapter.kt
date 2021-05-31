package com.gidm.cuidame.adapter

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gidm.cuidame.ChatActivity
import com.gidm.cuidame.R
import com.gidm.cuidame.Utils
import kotlin.collections.ArrayList

class MisSanitariosAdapter(private val sanitarios: ArrayList<Sanitario>):
    RecyclerView.Adapter<MisSanitariosAdapter.SanitarioHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SanitarioHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_mi_sanitario, false)
        return SanitarioHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SanitarioHolder, position: Int) {
        val itemSanitario = sanitarios[position]
        holder.bindSanitario(itemSanitario)
    }

    override fun getItemCount(): Int = sanitarios.size

    class SanitarioHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        private var sanitario: Sanitario? = null
        private var view: View = v

        init {
            view.setOnClickListener(this)
        }

        fun bindSanitario(sanitario: Sanitario) {
            this.sanitario = sanitario
            val nombre = view.findViewById<TextView>(R.id.nombreSanitario)
            val especialidad = view.findViewById<TextView>(R.id.especialidadSanitario)
            nombre.text = sanitario.nombre
            especialidad.text = sanitario.especialidad

            // Si clickea sobre la imagen "chat", ...
            val chat = view.findViewById<ImageView>(R.id.chat)
            chat.setOnClickListener{
                // Dirige a la actividad donde se muestran la lista de mensajes
                // entre los dos usuario
                val intent = Intent(view.context, ChatActivity::class.java)
                intent.putExtra("idSanitario", sanitario.uid)
                view.context.startActivity(intent)
            }

            // Si clickea sobre la basura, ...
            val borrar = view.findViewById<ImageView>(R.id.borrarSanitario)
            val id = view.context.getSharedPreferences("datos-paciente", MODE_PRIVATE).getString("id", "")!!
            borrar.setOnClickListener{
                Utils.borrarSanitario(sanitario.uid, id)
            }
        }

        override fun onClick(p0: View?) {}

    }
}