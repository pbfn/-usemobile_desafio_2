package com.example.catologo_filmes.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catologo_filmes.MovieRepositoryImp
import com.example.catologo_filmes.api.ApiListener
import com.example.catologo_filmes.connection.RoomConnection
import com.example.catologo_filmes.data.Movie

class MovieViewModel : ViewModel() {

    private val repository = MovieRepositoryImp()


    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _callBodyDetails = MutableLiveData<Movie>()
    var callBodyDetails: LiveData<Movie> = _callBodyDetails


    fun getDetailsFilms(id_film:String,context: Context){
        val db = RoomConnection(context).db()
        repository.getMovieDetails(id_film,object :ApiListener{
            override fun onSucess(body: Movie) {
                _callBodyDetails.value = body
                db.movieDao().insertMovie(body)
            }

            override fun onFailure(message: String) {
                _errorMessage.value = message
            }

        })

    }


}