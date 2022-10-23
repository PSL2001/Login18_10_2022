package com.example.login18_10_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.login18_10_2022.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    var nombre = ""
    var valor = 0
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        cogerDatos()
        setListeners()
    }

    private fun setListeners() {
        binding.btnVolver.setOnClickListener {
            volver()
        }
        binding.sbValor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                valor = p1
                pintarSeek()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    private fun pintarSeek() {
        binding.tvValor.text = String.format(getString(R.string.tvValor), valor)
    }

    private fun volver() {
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
        binding.etNombre2.setText(nombre)
        pintarSeek()
    }
}