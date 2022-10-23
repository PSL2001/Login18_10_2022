package com.example.login18_10_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login18_10_2022.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    var nombre = ""
    var valor = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        cogerDatos()
        setListeners()
    }

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
        val i = Intent()
        val bundle = Bundle().apply {
            putString(MainActivity.NOMBRE, nombre)
            putInt(MainActivity.VALOR, valor)
        }
        i.putExtras(bundle)
        setResult(RESULT_OK, i)
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