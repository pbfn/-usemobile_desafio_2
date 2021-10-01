package com.example.catologo_filmes.adapers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.ItemListMovieBinding
import com.squareup.picasso.Picasso

class AdapterMovie(private val movies: List<Movie>, val function: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<AdapterMovie.ViewHolder>() {


    class ViewHolder(view: ItemListMovieBinding) : RecyclerView.ViewHolder(view.root) {
        val imgMovie = view.imageMovie
        val titleMovie = view.textViewTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(movies[position].image).into(holder.imgMovie)
        holder.imgMovie.clipToOutline = true
        holder.titleMovie.text = movies[position].title
        holder.itemView.setOnClickListener {
            movies[position].let { it1 -> function(it1) }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}