package com.gidm.cuidame.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gidm.cuidame.ChatActivity
import com.gidm.cuidame.R

class ChatsAdapter(private val sanitarios: ArrayList<Sanitario>) :
    RecyclerView.Adapter<ChatsAdapter.SanitarioHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SanitarioHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_sanitario, false)
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
        }

        override fun onClick(p0: View?) {
            val intent = Intent(p0!!.context, ChatActivity::class.java)
            intent.putExtra("idSanitario", sanitario!!.uid)
            p0!!.context.startActivity(intent)
        }

    }
}
