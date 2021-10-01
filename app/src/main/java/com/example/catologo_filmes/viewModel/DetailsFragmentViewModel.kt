package com.example.catologo_filmes.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catologo_filmes.connection.RoomConnection
import com.example.catologo_filmes.data.Movie

class DetailsFragmentViewModel: ViewModel() {

    private var _movie = MutableLiveData<Movie>()
    var movie: LiveData <Movie> = _movie

    fun getMovieByIdFromDB(context: Context,idMovie:String) {
        val db = RoomConnection(context).db()
        _movie.value = db.movieDao().getMovieById(idMovie)
    }

}