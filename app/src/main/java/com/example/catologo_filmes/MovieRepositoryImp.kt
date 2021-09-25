package com.example.catologo_filmes

import com.example.catologo_filmes.api.ApiListener
import com.example.catologo_filmes.api.ApiService
import com.example.catologo_filmes.api.RespBody
import com.example.catologo_filmes.api.RetrofitInstace
import com.example.catologo_filmes.data.Movie
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImp:MovieRepository {

    private var remote: ApiService = RetrofitInstace().createService(ApiService::class.java)

    override fun getMovie(listener: ApiListener) {

        val call: Call<RespBody> = remote.getFilms()

        call.enqueue(object : Callback<RespBody>{
            override fun onResponse(call: Call<RespBody>, response: Response<RespBody>) {
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

            override fun onFailure(call: Call<RespBody>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

        })

    }

}