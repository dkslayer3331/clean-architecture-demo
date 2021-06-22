package com.dazai.movieappwithcleanarch.data.db

import androidx.room.TypeConverter
import com.dazai.movieappwithcleanarch.data.responses.GenreResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {

    @TypeConverter
    fun toJsonString(genres : List<GenreResponse>) = Gson().toJson(genres, object : TypeToken<List<GenreResponse>>() {}.type)

    @TypeConverter
    fun toList(json : String) = Gson().fromJson<List<GenreResponse>>(json, object : TypeToken<List<GenreResponse>>() {}.type)

}