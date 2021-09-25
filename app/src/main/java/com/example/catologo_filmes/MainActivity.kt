package com.example.catologo_filmes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.catologo_filmes.connection.RoomConnection
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonVoltar.visibility = View.GONE

//        val db = RoomConnection(this).db()
//        val movie1 = Movie("1","2","Teste de filme 1")
//        val movie2 = Movie("2","2","Teste de filme 2")
//        db.movieDao().insertAll(movie1,movie2)
//        db.movieDao().getAll()
//        db.movieDao().getAll()
    }
}