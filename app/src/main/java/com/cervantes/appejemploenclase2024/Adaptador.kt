package com.cervantes.appejemploenclase2024

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador: RecyclerView.Adapter<Adaptador.MiViewHolder>() {

    private var listaPersonas = ArrayList<Persona>()

    fun agregarPersonas(personas: ArrayList<Persona>){
        listaPersonas = personas
    }

    class MiViewHolder(var view: View):RecyclerView.ViewHolder(view) {

        private var filaNomyAp = view.findViewById<TextView>(R.id.filaNomyAp)
        private var filaDni = view.findViewById<TextView>(R.id.filaDni)
        private var filaCorreo = view.findViewById<TextView>(R.id.filaCorreo)

        //creamos un metodo para rellenar los objetos creados y asignados a los textViews de fila.xml

        fun rellenarFila(persona: Persona){
            //asi estamos rellenando los objetos con los datos que se cargan gracias al servicio creado en nuestro caso servicioWeb
            filaNomyAp.text= persona.per_nom + " " + persona.per_ape
            filaDni.text = persona.per_dni
            filaCorreo.text = persona.per_cor
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fila,parent,false)
    )

    //para enlazar o vincular los datos a las vistas (ViewHolder) en una posición específica de la lista
    override fun onBindViewHolder(holder: Adaptador.MiViewHolder, position: Int) {
        val personaItem = listaPersonas[position]
        holder.rellenarFila(personaItem)
    }
    //nos permite saber la cantidad de registros que se tiene en la lista
    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}