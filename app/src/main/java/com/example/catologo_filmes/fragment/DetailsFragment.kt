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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catologo_filmes.R
import com.example.catologo_filmes.adapers.AdapterActor
import com.example.catologo_filmes.data.ActorList
import com.example.catologo_filmes.data.Genre
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.FragmentDetailsBinding
import com.example.catologo_filmes.viewModel.DetailsFragmentViewModel
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var buttonVoltar: ImageView? = null
    private var textViewToolbar: TextView? = null

    private lateinit var idMovie: String
    private lateinit var idBack: String
    private lateinit var genre: String
    private lateinit var detailsFragmentViewModel: DetailsFragmentViewModel
    private lateinit var adapterRecycler: AdapterActor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        getArgs()
        setToolbar()
        observeData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setToolbar() {
        buttonVoltar = activity?.findViewById(R.id.button_back)
        buttonVoltar?.visibility = View.VISIBLE

        var direction = if (idBack == "home") {
            DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
        } else {
            DetailsFragmentDirections.actionDetailsFragmentToViewMoreFragment(genre)
        }
        buttonVoltar?.setOnClickListener {
            findNavController().navigate(direction)
        }
        textViewToolbar = activity?.findViewById(R.id.text_view_toolbar)
        textViewToolbar?.text = getText(R.string.details_title)
    }

    private fun getArgs() {
        val args: DetailsFragmentArgs by navArgs()
        idMovie = args.idMovie
        idBack = args.idBack
        genre = args.genre
        detailsFragmentViewModel.getMovieByIdFromDB(requireContext(), idMovie)
    }

    private fun setupViewModel() {
        detailsFragmentViewModel = ViewModelProvider(this).get(DetailsFragmentViewModel::class.java)
    }

    private fun observeData() {
        detailsFragmentViewModel.movie.observe(viewLifecycleOwner, { movie ->
            initViews(movie)
        })
    }

    private fun initViews(movie: Movie) {
        binding.apply {
            textViewTitleDetails.text = movie.fullTitle
            textViewAtoresDetails.text = movie.stars
            textViewSinopse.text = movie.details
            Picasso.get().load(movie.image).into(imageMovieDetails)
        }
        setRecyclerView(movie.actorList)
    }

    private fun setRecyclerView(actorList: List<ActorList>) {
        val layout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapterRecycler = AdapterActor(actorList)

        binding.recyclerViewActor.apply {
            layoutManager = layout
            adapter = adapterRecycler
        }
    }

}