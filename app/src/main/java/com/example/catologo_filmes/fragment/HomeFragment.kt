package com.example.catologo_filmes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.catologo_filmes.adapers.AdapterMovie
import com.example.catologo_filmes.data.Genre

import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.FragmentHomeBinding
import com.example.catologo_filmes.extensions.filterGenre


import com.example.catologo_filmes.viewModel.HomeFragmentViewModel
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterRecyclerAction: AdapterMovie
    private lateinit var adapterRecyclerDrama: AdapterMovie
    private lateinit var adapterRecyclerForYou: AdapterMovie
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        observeData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViewModel() {
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        homeFragmentViewModel.getMoviesFromDB(requireContext())
    }

    private fun observeData() {
        homeFragmentViewModel.movies.observe(viewLifecycleOwner, { movies ->
            initViews(movies)
            setRecyclerListSimpleHorizontal(movies)
        })
    }

    private fun initViews(movies: List<Movie>) {

        val movie = movies[0]
        binding.apply {
            textViewTitleLancamento.text = movie.fullTitle
            Picasso.get().load(movie.image).into(imageLancamento)
            imageLancamento.clipToOutline = true
            imageLancamento.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie.id, "home","all")
                findNavController().navigate(direction)
            }
            textViewAtores.text = movie.stars
        }

        binding.textViewViewMoreForYou.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToViewMoreFragment("For_You")
            findNavController().navigate(direction)
        }

        binding.textViewViewMoreAction.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToViewMoreFragment("Action")
            findNavController().navigate(direction)
        }

        binding.textViewViewMoreDrama.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToViewMoreFragment("Drama")
            findNavController().navigate(direction)
        }
    }

    private fun setRecyclerListSimpleHorizontal(movies: List<Movie>) {

        val layoutOne = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutTwo = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutThree = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapterRecyclerAction = AdapterMovie(movies.filterGenre("Action"), ::clickItem)
        adapterRecyclerDrama = AdapterMovie(movies.filterGenre("Drama"), ::clickItem)
        adapterRecyclerForYou = AdapterMovie(movies, ::clickItem)

        binding.recyclerViewForYou.apply {
            layoutManager = layoutOne
            adapter = adapterRecyclerForYou
        }

        binding.recyclerViewForAction.apply {
            layoutManager = layoutTwo
            adapter = adapterRecyclerAction
        }

        binding.recyclerViewForDrama.apply {
            layoutManager = layoutThree
            adapter = adapterRecyclerDrama
        }

    }

    private fun clickItem(movie: Movie) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie.id, "home","all")
        findNavController().navigate(direction)
    }

}