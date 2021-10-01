package com.example.catologo_filmes.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.data.MoviesID


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: Movie)

    @Query("SELECT COUNT(*) FROM movie")
    fun getCountMovie():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllIDs(vararg moviesID: MoviesID)

    @Query("SELECT * FROM movie WHERE id IN(:idMovie) ")
    fun getMovieById(idMovie: String): Movie

}