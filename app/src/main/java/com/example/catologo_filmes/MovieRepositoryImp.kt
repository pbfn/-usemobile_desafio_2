package com.example.catologo_filmes

import com.example.catologo_filmes.api.*
import com.example.catologo_filmes.data.Movie
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImp:MovieRepository {

    private var remote: ApiService = RetrofitInstace().createService(ApiService::class.java)

    override fun getMovieDetails(id_film:String,listener: ApiListener) {


        val call: Call<Movie> = remote.getDetailsFilms(id_film)

        call.enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.code() != 200) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let {
                        listener.onSucess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

        })

    }


}