package com.example.catologo_filmes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager

import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.FragmentViewMoreBinding

import com.example.catologo_filmes.adapers.AdapterMovieViewMore
import com.example.catologo_filmes.extensions.filterGenre
import com.example.catologo_filmes.viewModel.ViewMoreViewModel

class ViewMoreFragment : Fragment() {

    private var _binding: FragmentViewMoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewMoreViewModel: ViewMoreViewModel
    private lateinit var adapterRecycler: AdapterMovieViewMore
    private lateinit var genre: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getArgs()
        setView()
        setupViewModel()
        observeData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setView(){
        binding.textView.text = when(genre){
            "For_You" -> {
               "Para você"
            }
            "Action" ->{
                "Ação"
            }
            "Drama" ->{
                "Drama"
            }
            else -> {
                "Para você"
            }
        }
    }

    private fun getArgs() {
        val args: ViewMoreFragmentArgs by navArgs()
        genre = args.genre
    }


    private fun setupViewModel() {
        viewMoreViewModel = ViewModelProvider(this).get(ViewMoreViewModel::class.java)
        viewMoreViewModel.getFilmesFromDB(requireContext())
    }

    private fun observeData() {
        viewMoreViewModel.movies.observe(viewLifecycleOwner, { movies ->
            setRecyclerGrid(movies)
        })
    }


    private fun setRecyclerGrid(movies: List<Movie>) {
        val layout = GridLayoutManager(context, 2)

        adapterRecycler = when(genre){
            "For_You" -> {
                AdapterMovieViewMore(movies, ::clickItem)
            }
            "Action" ->{
                AdapterMovieViewMore(movies.filterGenre("Action"), ::clickItem)
            }
            "Drama" ->{
                AdapterMovieViewMore(movies.filterGenre("Drama"), ::clickItem)
            }
            else -> {
                AdapterMovieViewMore(movies, ::clickItem)
            }

        }


        binding.recyclerViewViewMore.apply {
            layoutManager = layout
            adapter = adapterRecycler
        }
    }

    private fun clickItem(movie: Movie) {
        val direction =
            ViewMoreFragmentDirections.actionViewMoreFragmentToDetailsFragment(movie.id, "viewMore", genre)
        findNavController().navigate(direction)
    }

}