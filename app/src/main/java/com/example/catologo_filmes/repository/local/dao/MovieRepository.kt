package com.example.catologo_filmes.repository.local.dao

import com.example.catologo_filmes.api.ApiListener
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.data.MoviesID
import com.example.catologo_filmes.data.MoviesIdResponse


interface MovieRepository {
    fun getMovieDetails(id_film:String ,listener: ApiListener<Movie>)

    fun getIdMovies(listener: ApiListener<MoviesIdResponse>)
}