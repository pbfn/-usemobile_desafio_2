package com.example.catologo_filmes.api

import com.example.catologo_filmes.data.Movie
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiService {
    @GET("en/API/Top250Movies/k_i0hv7eqr")
    fun getFilms(): Call<RespBody>

}