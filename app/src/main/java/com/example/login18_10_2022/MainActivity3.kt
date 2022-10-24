package com.example.login18_10_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login18_10_2022.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    //Valores
    lateinit var binding: ActivityMain3Binding
    var nombre = ""
    var valor = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Creamos el binding
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Cogemos los datos del activity
        cogerDatos()
        //Ponemos los listeners
        setListeners()
    }
    //---------------------------------------------------------------------------------------------
    /*
     * Añade los listeners a cada objeto que nos interesa
     * E: Nada
     * S: Nada
     */
    private fun setListeners() {
        binding.btnClick.setOnClickListener {
            valor++
            pintarClicks()
        }
        binding.btnVolver2.setOnClickListener {
            enviarDatos()
        }
    }

    private fun enviarDatos() {
        //Creamos un Intent
        val i = Intent()
        //Creamos un bundle
        val bundle = Bundle().apply {
            putString(MainActivity.NOMBRE, nombre)
            putInt(MainActivity.VALOR, valor)
        }
        //Ponemos el bundle en el intent
        i.putExtras(bundle)
        //Mandamos de resultado ok con el intetn
        setResult(RESULT_OK, i)
        //Y terminamos
        finish()
    }

    private fun cogerDatos() {
        val datos = intent.extras
        nombre = datos?.get(MainActivity.NOMBRE) as String
        binding.etNombre3.setText(nombre)
        pintarClicks()
    }

    private fun pintarClicks() {
        binding.tvClicks.text = valor.toString()
    }
}