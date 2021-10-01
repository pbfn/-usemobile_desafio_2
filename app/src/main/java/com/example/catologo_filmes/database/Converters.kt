package com.example.catologo_filmes.database

import androidx.room.TypeConverter
import com.example.catologo_filmes.data.ActorList
import com.example.catologo_filmes.data.Genre
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<ActorList>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<ActorList> {
        val objects = Gson().fromJson(value, Array<ActorList>::class.java) as Array<ActorList>
        val list = objects.toList()
        return list
    }


    @TypeConverter
    fun genreToJson(value: List<Genre>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun genreToList(value: String): List<Genre> {
        val objects = Gson().fromJson(value, Array<Genre>::class.java) as Array<Genre>
        val list = objects.toList()
        return list
    }

}