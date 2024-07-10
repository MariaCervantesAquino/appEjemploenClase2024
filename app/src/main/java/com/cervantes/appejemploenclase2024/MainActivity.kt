package com.cervantes.appejemploenclase2024

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rvPersonas: RecyclerView
    private lateinit var btnRegistrar: FloatingActionButton

    private var adaptador : Adaptador = Adaptador()
    private var listaPersonas = ArrayList<Persona>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()
        cargarPersonas()
    }

    private fun cargarPersonas(){
        CoroutineScope(Dispatchers.IO).launch {
            //creamos una variable del tipo RetrofitClient y entramos al objeto webService de la clase que creamos WebService
            // y de este llamamos a la definicion q deseemos, en este caso : obtenerPersonas
            val rspta = RetrofitClient.webService.obtenerPersonas()
            runOnUiThread{
                if(rspta.isSuccessful){
                    listaPersonas = rspta.body()!!.listaPersonas
                    mostrarPersonas()
                }

            }
        }
    }
    private fun mostrarPersonas(){
        adaptador.agregarPersonas(listaPersonas)
        rvPersonas.adapter= adaptador
    }
    private fun asignarReferencias(){

        rvPersonas = findViewById(R.id.rvPersonas)
        rvPersonas.layoutManager = LinearLayoutManager(this)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener{
            val intent = Intent(this,FormularioActivity::class.java)
            startActivity(intent)
        }
    }




}