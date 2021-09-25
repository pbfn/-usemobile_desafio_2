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
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.buttonVoltar.visibility = View.GONE

        setUpViewModel()
        clickEvent()
        observeData()

//        val db = RoomConnection(this).db()
//        val movie1 = Movie("1","2","Teste de filme 1")
//        val movie2 = Movie("2","2","Teste de filme 2")
//        db.movieDao().insertAll(movie1,movie2)
//        db.movieDao().getAll()
//        db.movieDao().getAll()
    }

    private fun setUpViewModel() {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    private fun observeData() {
        movieViewModel.callBody.observe(this, { respBody ->
            Toast.makeText(this, respBody.items.get(0).title, Toast.LENGTH_LONG).show()
        })

        movieViewModel.errorMessage.observe(this, { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    private fun clickEvent() {
        binding.apply {
            buttonText.setOnClickListener {
                movieViewModel.getFilmes()
            }
        }
    }
}