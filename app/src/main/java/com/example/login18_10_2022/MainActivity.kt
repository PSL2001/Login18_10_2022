package com.example.login18_10_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.login18_10_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object KEYS {
        val NOMBRE = "nombre"
        val PASSWORD = "pass"
        val VALOR = "valor"
    }
    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when(it.resultCode) {
            RESULT_OK -> {
                //Recogemos los datos
                nombre = it.data?.getStringExtra(NOMBRE).toString()
                valor = it.data?.getIntExtra(VALOR, 0).toString()
                mostrarToast("$nombre ha devuelto $valor")
            }
            RESULT_CANCELED -> {
                mostrarToast("El usuario ha cancelado")
            }
        }
    }

    private fun mostrarToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    lateinit var binding: ActivityMainBinding
    val usuarios = arrayOf("Admin", "Ana", "Juan")
    val password = arrayOf("secret0", "passAna", "passJuan")
    var nombre = ""
    var pass = ""
    var valor = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }
    //---------------------------------------------------------------------------------------------
    private fun setListeners() {
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        nombre = binding.etUsuario.text.toString().trim()
        if (nombre.isEmpty()) {
            binding.etUsuario.setError("Este campo es obligatorio")
            binding.etUsuario.requestFocus()
            return
        }
        pass = binding.etPass.text.toString()
        if (pass.isEmpty()) {
            binding.etPass.setError("Este campo es obligatorio")
            binding.etPass.requestFocus()
            return
        }
        if (comprobarLogin(nombre, pass)) {
            if (nombre == "Admin") {
                //Nos vamos al activity 2
                irActivity(2)
            } else {
                //Nos vamos al activity 3
                irActivity(3)
            }
        } else {
            mostrarError("Usuario o Contraseña incorrectos")
        }

    }

    private fun irActivity(i: Int) {
        when (i) {
            2 -> {
                val i = Intent(this, MainActivity2::class.java)
                val bundle = Bundle().apply {
                    putString(NOMBRE, nombre)
                    putString(PASSWORD, pass)
                }
                i.putExtras(bundle)
                responseLauncher.launch(i)
            }
            3 -> {
                val i = Intent(this, MainActivity3::class.java)
                val bundle = Bundle().apply {
                    putString(NOMBRE, nombre)
                    putString(PASSWORD, pass)
                }
                i.putExtras(bundle)
                responseLauncher.launch(i)
            }
        }
    }

    //---------------------------------------------------------------------------------------------
    private fun mostrarError(s: String) {
        val alerta = AlertDialog.Builder(this)
            .setTitle("** ERROR **")
            .setMessage(s)
            .setPositiveButton("ACEPTAR", null)
            .show()
    }

    //---------------------------------------------------------------------------------------------
    /*
    * E: nombre (String), pass(String)
    * S: Boolean
    * Esta función comprueba si el usuario y contraseña introducidos en la aplicacion son correctos
     */
    private fun comprobarLogin(nombre: String, pass: String): Boolean {
        //Primero comprobamos que el usuario es el correcto
        for (usuario in usuarios) { //Para ello recorremos el array de usuarios
            if (usuario == nombre) { //Si el usuario es correcto
                for (contra in password) { //Comenzamos a recorrer las contraseñas
                    if (contra == pass) { //Si encuentra una donde la contraseña sea la misma
                        return true //Devolvemos un true
                    } //Fin segundo if
                } //Fin segundo for
            } //Fin del primer if
        } //Fin del primer for
        //Si despues de pasar por ambos bucles no se encuentra nada, entonce es que el usuario o la contraseña no son correctos
        return false //Entonces devolvemos false
    }
    //---------------------------------------------------------------------------------------------
}