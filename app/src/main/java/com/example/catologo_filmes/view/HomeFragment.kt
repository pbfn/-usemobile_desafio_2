package com.example.catologo_filmes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catologo_filmes.DataProvider
import com.example.catologo_filmes.R
import com.example.catologo_filmes.RecyclerViewAdapterMovie
import com.example.catologo_filmes.connection.RoomConnection
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.FragmentHomeBinding
import com.example.catologo_filmes.viewModel.MovieViewModel
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var buttonVoltar: ImageView? = null
    private var textViewToolbar: TextView? = null


    private lateinit var adapterRecycler: RecyclerViewAdapterMovie


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setToolbar()
        initViews()
        setRecyclerListSimpleHorizontal()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setToolbar() {
        buttonVoltar = activity?.findViewById(R.id.button_voltar)
        buttonVoltar?.visibility = View.GONE
        textViewToolbar = activity?.findViewById(R.id.text_view_toolbar)
        textViewToolbar?.text = getText(R.string.app_name)
    }


    private fun initViews(){
        val db = activity?.let { RoomConnection(it.applicationContext).db() }
        val movie:Movie? = db?.movieDao()?.getAll()?.get(0)
        binding.apply {
            textViewTitleLancamento.text = movie?.fullTitle.toString()
            Picasso.get().load(movie?.image).into(imageLancamento)
            imageLancamento.clipToOutline= true
            textViewAtores.text = movie?.stars
        }
    }

    fun setRecyclerListSimpleHorizontal() {
        val layoutOne = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL, false)
        val layoutTwo = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL, false)
        adapterRecycler = RecyclerViewAdapterMovie(DataProvider.MovieList, ::clickItem)
        binding.recyclerViewForAcao.apply {
                layoutManager = layoutOne
                adapter = adapterRecycler
        }
        binding.recyclerViewForYou.apply {
            layoutManager = layoutTwo
            adapter = adapterRecycler
        }
    }

    fun clickItem(name: String){
        //Toast.makeText(this, "$name" , Toast.LENGTH_SHORT).show()
    }

}