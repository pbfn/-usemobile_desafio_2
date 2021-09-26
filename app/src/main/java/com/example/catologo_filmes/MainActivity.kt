package com.example.catologo_filmes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.catologo_filmes.connection.RoomConnection
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.ActivityMainBinding
import com.example.catologo_filmes.viewModel.MovieViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        insertItens()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonVoltar.visibility = View.VISIBLE

       // setUpViewModel()
    }

    private fun setUpViewModel() {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getDetailsFilms("tt0388629",this)
    }

    private fun observeData() {
        val db = RoomConnection(this).db()
        movieViewModel.callBodyDetails.observe(this, { movie ->
            Toast.makeText(this, movie.title, Toast.LENGTH_LONG).show()
        })

        movieViewModel.errorMessage.observe(this, { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    private fun clickEvent() {
        binding.apply {
            buttonVoltar.setOnClickListener {

            }
        }
    }


    private fun insertItens(){
        val db = RoomConnection(this).db()
        for ( movie in DataProvider.MovieList){
            db.movieDao().insertAll(movie)
        }
        db.movieDao().getAll()
    }

}