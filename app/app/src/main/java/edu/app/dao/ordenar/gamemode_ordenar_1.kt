package edu.app.dao.ordenar

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import edu.app.dao.R
import edu.app.dao.databinding.FragmentGamemodeOrdenarBinding
import edu.app.dao.fragments.Muralla

class FragmentoOrdenar : Fragment() {

    private lateinit var binding: FragmentGamemodeOrdenarBinding
    private lateinit var targetSentenceTextView: TextView
    private lateinit var characterBarLayout: LinearLayout
    private lateinit var keyboardGridLayout: GridLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGamemodeOrdenarBinding.inflate(inflater, container, false)
        characterBarLayout = binding.characterBarLayout
        keyboardGridLayout = binding.keyboardGridLayout
        setupKeyboard()
        val devolverFlecha = requireActivity().findViewById<LinearLayout>(R.id.flecha_devolver)
        val flechaDevolverImagen =
            requireActivity().findViewById<ImageButton>(R.id.flecha_devolver_imagen)
        devolverFlecha.visibility = View.VISIBLE

        flechaDevolverImagen.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            val muralla = Muralla()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, muralla)
                commit()
            }
        }

        return binding.root
    }

    private fun setupKeyboard() {
        val keyboardCharacters = listOf(
            '你', '好', '吗', '？'
        )
        val buttonMargin = resources.getDimensionPixelSize(R.dimen.dim_2dp)
        keyboardCharacters.forEach { char ->
            val button = Button(requireContext())
            button.text = char.toString()
            button.typeface =
                Typeface.createFromAsset(requireContext().assets, "fonts/ma_shan_zheng.ttf")
            button.textSize = 40F
            button.setBackgroundResource(R.drawable.button_border)
            val layoutParams = GridLayout.LayoutParams().apply {
                width = GridLayout.LayoutParams.WRAP_CONTENT
                height = GridLayout.LayoutParams.WRAP_CONTENT
                marginStart = buttonMargin // Set margin for the start side
                marginEnd = buttonMargin // Set margin for the end side
                topMargin = buttonMargin // Set margin for the top
                bottomMargin = buttonMargin // Set margin for the bottom
            }
            button.layoutParams = layoutParams
            button.setOnClickListener {
                characterBarLayout.addView(Button(requireContext()).apply {
                    text = char.toString()
                    typeface =
                        Typeface.createFromAsset(requireContext().assets, "fonts/ma_shan_zheng.ttf")
                    textSize = 40F
                    setBackgroundResource(R.drawable.button_border)
                    val layoutParams2 = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams2.setMargins(
                        buttonMargin,
                        0,
                        buttonMargin,
                        0
                    ) // Set margins (left, top, right, bottom)
                    setLayoutParams(layoutParams2)
                    setOnClickListener { characterBarLayout.removeView(this)
                        if (this.parent != null){
                            (this.parent as? ViewGroup)?.removeView(this)
                        }
                        keyboardGridLayout.addView(this) }
                })
                keyboardGridLayout.removeView(button)
            }
            keyboardGridLayout.addView(button)
        }
    }
}