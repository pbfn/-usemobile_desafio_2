package com.example.catologo_filmes.api

interface ApiListener<T> {

    fun onSucess(body: T)

    fun onFailure(message:String)

}