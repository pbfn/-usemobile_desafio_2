package com.example.catologo_filmes.api

import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.data.MoviesIdResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("en/API/Top250Movies/k_7gn1n6r2")
    fun getIdFilms(): Call<MoviesIdResponse>


    //k_i0hv7eqr //k_c57x8czn //k_7gn1n6r2
    @GET("pt/API/title/k_7gn1n6r2/{id_film}")
    fun getDetailsFilms(@Path("id_film") id_filme: String): Call<Movie>
}