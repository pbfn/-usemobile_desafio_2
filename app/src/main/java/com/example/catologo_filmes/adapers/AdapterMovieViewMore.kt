package com.example.catologo_filmes.adapers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.ItemListMovieViewMoreBinding
import com.squareup.picasso.Picasso

class AdapterMovieViewMore(private val movies: List<Movie>, val getMovie: (movie: Movie) -> Unit):
    RecyclerView.Adapter<AdapterMovieViewMore.ViewHolder>() {

    class ViewHolder(view: ItemListMovieViewMoreBinding) : RecyclerView.ViewHolder(view.root) {
        val imgMovie = view.imageMovie
        val titleMovie = view.textViewTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListMovieViewMoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(movies[position].image).into(holder.imgMovie)
        holder.imgMovie.clipToOutline= true
        holder.titleMovie.text = movies[position].title
        holder.itemView.setOnClickListener{
            getMovie(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}