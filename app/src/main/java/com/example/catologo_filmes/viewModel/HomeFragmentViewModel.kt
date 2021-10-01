package com.example.catologo_filmes.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catologo_filmes.connection.RoomConnection
import com.example.catologo_filmes.data.Movie

class HomeFragmentViewModel: ViewModel() {


    private var _movies = MutableLiveData<List<Movie>>()
    var movies: LiveData<List<Movie>> = _movies

    fun getMoviesFromDB(context: Context) {
        val db = RoomConnection(context).db()
        _movies.value = db.movieDao().getAll()
    }

}