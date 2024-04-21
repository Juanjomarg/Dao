package edu.app.dao.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.app.dao.R
import edu.app.dao.databinding.FragmentEditarPerfilBinding
import edu.app.dao.funciones.GlobalData

class EditarPerfil : Fragment() {

    private lateinit var binding: FragmentEditarPerfilBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding para controlar los elementos del fragment_editar_perfil.xml
        binding = FragmentEditarPerfilBinding.inflate(inflater, container, false)

        // Instancia de la base de datos
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()

        // Referencia a la base de datos con los datos de la ID que se capturó en la clase GlobalData
        val refUsuarios: DatabaseReference = database.getReference("Usuarios").child(GlobalData.idCurrent)

        // Instancia del fragmento anterior (yop.kt)
        val yop = yop()

        // Permite cambiar el título de la barra de texto y también el tamaño de la letra de este
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        val toolbarText = requireActivity().findViewById<TextView>(R.id.toolbar_title)
        toolbarText.text = "Información personal"
        toolbarText.textSize = 30F

        // Cuando se le da clic a la toolbar superior se devuelve al fragmento de yop.kt
        toolbar.setOnClickListener {
            (activity as InicioPrincipal).makeCurrentFragment(yop)
        }

        // Cambia los datos en la base de datos con los que se tengan en los campos llenados de
        // esta parte de editar perfil. Se cambian todos los campos en la base de datos
        binding.buttonGuardar.setOnClickListener {
            val newUserName = binding.nombreUsuarioEditar.text.toString()
            val newPassword = binding.contrasenaUsuarioEditar.text.toString()
            val newNombre = binding.nombreCompletoEditar.text.toString()
            val newDescription = binding.presentacionEditar.text.toString()
            val nuevosDatos = hashMapOf<String, Any>(
                "username" to newUserName,
                "password" to newPassword,
                "nameFull" to newNombre,
                "description" to newDescription
            )
            (activity as InicioPrincipal).makeCurrentFragment(yop)
            refUsuarios.updateChildren(nuevosDatos).addOnSuccessListener {
                Toast.makeText(requireContext(), "Datos actualizados con éxito!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { error ->
                Toast.makeText(requireContext(), "Error: $error", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


}