package com.example.catologo_filmes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catologo_filmes.MovieRepositoryImp
import com.example.catologo_filmes.api.ApiListener
import com.example.catologo_filmes.api.RespBody
import com.example.catologo_filmes.data.Movie

class MovieViewModel : ViewModel() {

    private val repository = MovieRepositoryImp()

    private var _callBody = MutableLiveData<RespBody>()
    var callBody: LiveData<RespBody> = _callBody

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage


    fun getFilmes() {

        repository.getMovie(object : ApiListener {
            override fun onSucess(body: RespBody) {
                _callBody.value = body
            }
            override fun onFailure(message: String) {
                _errorMessage.value = message
            }

        })

    }


}