package edu.app.dao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import edu.app.dao.databinding.WelcomeToAppLayoutBinding

class WelcomeToApp : AppCompatActivity() {

    /*
        Declaración del binding con el layout welcome_to_app_layout
        El nombre WelcomeToAppLayoutBinding sale del archivo .xml
        mencionado anteriormente y se genera automáticamente.
     */
    private lateinit var binding: WelcomeToAppLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se utiliza el binding para inflar la vista y meterse como raiz
        binding = WelcomeToAppLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botonLogin = binding.welcomeButtonLogin

        botonLogin.setOnClickListener {
            val intent = Intent(this, LoginToApp::class.java)
            startActivity(intent)
        }

        // Se accede al id del botón de inicio registrar a través del binding
        val botonRegistro = binding.welcomeButtonRegister

        botonRegistro.setOnClickListener {
            val intent = Intent(this, RegisterToApp::class.java)
            startActivity(intent)
        }
    }
}