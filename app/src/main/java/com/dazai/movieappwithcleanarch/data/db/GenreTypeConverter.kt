package com.dazai.movieappwithcleanarch.data.db

import androidx.room.TypeConverter
import com.dazai.movieappwithcleanarch.data.models.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {

    @TypeConverter
    fun toJsonString(genres : List<Genre>) = Gson().toJson(genres, object : TypeToken<List<Genre>>() {}.type)

    @TypeConverter
    fun toList(json : String) = Gson().fromJson<List<Genre>>(json, object : TypeToken<List<Genre>>() {}.type)

}