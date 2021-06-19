package com.dazai.movieappwithcleanarch.app.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity

fun ImageView.showImage(url : String) = Glide.with(this.context).load(url).into(this)

fun AppCompatActivity.showToast(message : String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/** model to entity mappers **/
fun MovieVO.toEntity() = with(this) {
    MovieEntity(
            id = id.toInt(),
            title = title,
            posterPath = "$IMAGE_ENDPOINT${posterPath}",
            originalTitle = originalTitle
    )
}

fun MovieDetailResponse.toEntity() = with(this){
    MovieDetailEntity(
            id = id,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            genres = genres,
            posterPath = posterPath, // could be different image endpoint
            backDropPath = backdropPath
    )
}

