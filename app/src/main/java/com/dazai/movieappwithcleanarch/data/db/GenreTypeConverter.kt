package com.dazai.movieappwithcleanarch.data.db

import androidx.room.TypeConverter
import com.dazai.movieappwithcleanarch.data.responses.GenreResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType


class GenreTypeConverter {

    private val moshi = Moshi.Builder().build()

    private val listType = newParameterizedType(
        List::class.java,
        GenreResponse::class.java
    )

    private val adapter : JsonAdapter<List<GenreResponse>> = moshi.adapter(listType)

    @TypeConverter
    fun toJsonString(genres : List<GenreResponse>) : String {
        return adapter.toJson(genres)
    }

    @TypeConverter
    fun toList(json : String) = adapter.fromJson(json)

}