package com.example.catologo_filmes.extensions

import com.example.catologo_filmes.data.Movie


fun List<Movie>.filterGenre(filter: String):MutableList<Movie>{
    val movies = this
    val listReturn:MutableList<Movie> = ArrayList()
    for (movie in movies){

        if(movie.genreList[0].value == filter){
                listReturn.add(movie)

        }
    }
    return listReturn
}