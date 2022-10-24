package com.example.login18_10_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.login18_10_2022.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    //Variables
    var nombre = ""
    var valor = 0
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Creamos el binding
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Recogemos datos del Activity 1
        cogerDatos()
        //Ponemos los listeners
        setListeners()
    }
    //---------------------------------------------------------------------------------------------
    /*
     * La funcion añade los listeners a los objetos correspondientes
     * E: Nada
     * S: Nada
     */
    private fun setListeners() {
        binding.btnVolver.setOnClickListener {
            //Cuando pulsemos volver, procesará el valor del seekbar y lo devolverá
            volver()
        }
        binding.sbValor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //Implementamos las funciones de SeekBar aunque solo nos interesa 1
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                //Cada vez que hubiera un cambio en el seekbar, guardamos el dato en valor
                valor = p1
                //Y pintamos en el textView correspondiente
                pintarSeek()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }
    //---------------------------------------------------------------------------------------------
    /*
     * Pinta el campo de textView el valor dado
     * E: Nada
     * S: Nada
     */
    private fun pintarSeek() {
        binding.tvValor.text = String.format(getString(R.string.tvValor), valor)
    }
    //---------------------------------------------------------------------------------------------
    /*
     * Devuelve los datos al ActivityMain
     * E: Nada
     * S: Nada
     */
    private fun volver() {
        //Creamos un Intent
        val i = Intent()
        //Y un bundle
        val bundle = Bundle().apply {
            putString(MainActivity.NOMBRE, nombre)
            putInt(MainActivity.VALOR, valor)
        }
        //Al intent le añadimos el bundle
        i.putExtras(bundle)
        //Ponemos el resultado como OK y le pasamos al intent
        setResult(RESULT_OK, i)
        //Y volvemos al intent
        finish()
    }

    private fun cogerDatos() {
        // Recogemos los datos del otro intent
        val datos = intent.extras
        // Asignamos cada clave del intent con una variable
        nombre = datos?.get(MainActivity.NOMBRE) as String
        //Ponemos el nombre del usuario
        binding.etNombre2.setText(nombre)
        // Y pintamos el seek
        pintarSeek()
    }
}