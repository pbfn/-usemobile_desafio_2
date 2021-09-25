package com.example.catologo_filmes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.catologo_filmes.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var buttonVoltar: ImageView? = null
    private var textViewToolbar: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setToolbar()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setToolbar() {
        buttonVoltar = activity?.findViewById(R.id.button_voltar)
        buttonVoltar?.visibility = View.GONE
        textViewToolbar = activity?.findViewById(R.id.text_view_toolbar)
        textViewToolbar?.text = String.format(R.string.app_name.toString())
    }

}