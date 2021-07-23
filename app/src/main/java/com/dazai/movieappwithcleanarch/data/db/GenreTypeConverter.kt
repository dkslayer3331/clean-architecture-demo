package com.dazai.movieappwithcleanarch.data.db

import androidx.room.TypeConverter
import com.dazai.movieappwithcleanarch.data.responses.GenreResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType


class GenreTypeConverter {

    var adapter: JsonAdapter<List<GenreResponse>>

    init {
         val moshi = Moshi.Builder().build()

         val listType = newParameterizedType(
            List::class.java,
            GenreResponse::class.java
        )

        adapter  = moshi.adapter(listType)
    }

    @TypeConverter
    fun toJsonString(genres : List<GenreResponse>)  = adapter.toJson(genres)

    @TypeConverter
    fun toList(json : String) = adapter.fromJson(json)

}