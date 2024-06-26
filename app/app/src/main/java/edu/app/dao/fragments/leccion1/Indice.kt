/*
    Copyright (C) 2024 Alvarado Ludwig & Martinez Juan Jos�.

    This file is part of Dao.

    Dao is free software: you can redistribute it and/or modify it under the terms of the
    GNU General Public License as published by the Free Software Foundation, either version 3 of
    the License, or any later version.

    Dao is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
    without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
    the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along with this program. If
    not, see <https://www.gnu.org/licenses/>.
 */
package edu.app.dao.fragments.leccion1

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import edu.app.dao.R
import edu.app.dao.databinding.FragmentIndiceBinding
import edu.app.dao.fragments.Tao
import edu.app.dao.fragments.leccion1.ui.theme.Leccion1Lectura1
import edu.app.dao.fragments.leccion1.ui.theme.Leccion1PalabrasNuevas

class Indice : Fragment() {

    private lateinit var binding: FragmentIndiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIndiceBinding.inflate(inflater, container, false)
        val toolbarText = requireActivity().findViewById<TextView>(R.id.toolbar_title)
        val toolbar = requireActivity().findViewById<FrameLayout>(R.id.frame_layout_bar_buttom)
        val flechaDevolver = requireActivity().findViewById<LinearLayout>(R.id.flecha_devolver)
        val flechaDevolverImagen =
            requireActivity().findViewById<ImageButton>(R.id.flecha_devolver_imagen)
        toolbarText.text = "你好"
        toolbarText.textSize = 35F
        toolbarText.typeface =
            Typeface.createFromAsset(requireContext().assets, "fonts/ma_shan_zheng.ttf")
        toolbar.visibility = View.GONE
        flechaDevolver.visibility = View.VISIBLE
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        flechaDevolverImagen.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            val tao = Tao()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, tao)
                commit()
            }
        }

        binding.buttonPalabrasNuevas.setOnClickListener {
            val leccion1PalabrasNuevas = Leccion1PalabrasNuevas()
            navegarFragmento(leccion1PalabrasNuevas)
        }

        binding.buttonLectura1.setOnClickListener {
            val leccion1Lectura1 = Leccion1Lectura1()
            navegarFragmento(leccion1Lectura1)
        }

        binding.buttonLectura2.setOnClickListener {
            val leccion1Lectura2 = Leccion1Lectura2()
            navegarFragmento(leccion1Lectura2)
        }

        binding.buttonNotas.setOnClickListener {
            val leccion1Notas = Leccion1Notas()
            navegarFragmento(leccion1Notas)
        }

        binding.buttonEjerciciosFonetica.setOnClickListener {
            val leccion1EjerciciosFonetica = Leccion1EjerciciosFonetica()
            navegarFragmento(leccion1EjerciciosFonetica)
        }

        binding.buttonConversacion.setOnClickListener {
            val leccion1PracticaConversacion = Leccion1PracticaConversacion()
            navegarFragmento(leccion1PracticaConversacion)
        }

        binding.buttonFonetica.setOnClickListener {
            val leccion1Fonetica = Leccion1Fonetica()
            navegarFragmento(leccion1Fonetica)
        }

        binding.buttonGramatica.setOnClickListener {
            Toast.makeText(requireContext(), "Clic en gramática", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    private fun navegarFragmento(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

}
