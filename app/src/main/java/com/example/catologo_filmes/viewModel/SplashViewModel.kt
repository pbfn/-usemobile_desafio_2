package com.example.catologo_filmes.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catologo_filmes.api.ApiListener
import com.example.catologo_filmes.connection.RoomConnection
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.data.MoviesID
import com.example.catologo_filmes.data.MoviesIdResponse
import com.example.catologo_filmes.database.AppDataBase
import com.example.catologo_filmes.repository.local.dao.MovieRepositoryImp

class SplashViewModel: ViewModel() {



    private val repository = MovieRepositoryImp()

    private var _filmesId = MutableLiveData<MoviesIdResponse>()

    var filmesId: LiveData<MoviesIdResponse> = _filmesId

    private var _callBodyDetails = MutableLiveData<Movie>()
    var callBodyDetails: LiveData<Movie> = _callBodyDetails

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage


    fun getIdMovies(context: Context){
        val db = RoomConnection(context).db()
        repository.getIdMovies(object: ApiListener<MoviesIdResponse>{
            override fun onSucess(body: MoviesIdResponse) {
                _filmesId.value = body
                insertDB(db,body)
            }

            override fun onFailure(message: String) {
                _errorMessage.value = message
            }

        })
    }
    private fun insertDB(db: AppDataBase, body: MoviesIdResponse) {
        for(items in body.items){
            db.movieDao().insertAllIDs(items)
        }
    }

    fun getDetailsFilms(id_film: String, context: Context) {
        val db = RoomConnection(context).db()
        db.movieDao().getAll()
        repository.getMovieDetails(id_film, object : ApiListener<Movie> {
            override fun onSucess(body: Movie) {
                _callBodyDetails.value = body
                if(body != null){
                    db.movieDao().insertMovie(body)
                }
            }

            override fun onFailure(message: String) {
                _errorMessage.value = message
            }
        })
    }


     fun getMovies(context: Context):Int{
        val db = RoomConnection(context).db()
        return db.movieDao().getCountMovie()
    }

}