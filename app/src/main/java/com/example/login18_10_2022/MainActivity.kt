package com.example.login18_10_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.login18_10_2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Claves constantes para activities
    companion object KEYS {
        val NOMBRE = "nombre"
        val PASSWORD = "pass"
        val VALOR = "valor"
    }
    //Variable para lidiar con los resultados de los activities
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
    /*
     * E: String
     * S: Nada; Muestra un toast con el texto que le pasamos
     */
    private fun mostrarToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }
    //Variables
    lateinit var binding: ActivityMainBinding //Creamos un binding para este activity
    //Los arrays para los usuarios
    val usuarios = arrayOf("Admin", "Ana", "Juan")
    val password = arrayOf("secret0", "passAna", "passJuan")
    var nombre = ""
    var pass = ""
    var valor = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflamos el binding con los objetos del layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Mostramos el contenido al usuario
        setContentView(binding.root)
        //Ponemos los listeners
        setListeners()
    }
    //---------------------------------------------------------------------------------------------
    /*
     * Añade listeners para todos los campos interesados
     *
     * E: Nada
     * S: Nada
     */
    private fun setListeners() {
        binding.btnLogin.setOnClickListener {
            //Cuando se pulse el btnLogin se llama a la funcion correspondiente
            login()
        }
    }
    /*
     * Comprueba los datos y si estos son correctos, vamos al activity correspondiente
     * E: Nada
     * S: Nada
     */
    private fun login() {
        //Primero comprobamos que nombre no está vacio
        nombre = binding.etUsuario.text.toString().trim()
        if (nombre.isEmpty()) { //Si se diera el caso, entonces
            //Ponemos un error y decimos al usuario que este campo es obligatorio
            binding.etUsuario.setError("Este campo es obligatorio")
            binding.etUsuario.requestFocus()
            //Y salimos
            return
        }
        //Hacemos lo mismo para la password
        pass = binding.etPass.text.toString()
        if (pass.isEmpty()) {
            binding.etPass.setError("Este campo es obligatorio")
            binding.etPass.requestFocus()
            return
        }
        //Si hemos llegado aqui entonces ni el usuario ni la contraseña estan vacios
        if (comprobarLogin(nombre, pass)) { //Comprobamos si el login es correcto
            //Si es el caso entonces comprobamos si el nombre es "Admin"
            if (nombre == "Admin") { //Si se da ese caso
                //Nos vamos al activity 2
                irActivity(2)
            } else { //En otro caso
                //Nos vamos al activity 3
                irActivity(3)
            }
        } else { //Si el login no es correcto
            //Entonces mostramos un error
            mostrarError("Usuario o Contraseña incorrectos")
        }

    }
    //---------------------------------------------------------------------------------------------
    /*
     * Dependiendo sobre que numero se le pase al parametro, ira a un activity u otro
     * E: Integer
     * S: Nada
     */
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
    /*
     * La funcion muestra una alerta de error con un mensaje
     * E: String
     * S: Nada
     */
    private fun mostrarError(s: String) {
        AlertDialog.Builder(this)
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