package com.example.catologo_filmes.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstace {

    companion object {
        private lateinit var retrofit: Retrofit

        private var base_url = "https://imdb-api.com/"

        fun getRetrofit(): Retrofit {
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    }

    fun <S> createService(service: Class<S>):S{
        return getRetrofit().create(service)
    }

}