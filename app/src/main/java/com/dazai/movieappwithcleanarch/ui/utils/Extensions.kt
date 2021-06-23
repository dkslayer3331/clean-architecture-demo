package com.dazai.movieappwithcleanarch.ui.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity
import com.dazai.movieappwithcleanarch.data.responses.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.responses.MovieResponse
import com.dazai.movieappwithcleanarch.domain.entities.Movie

fun ImageView.showImage(url: String) = Glide.with(this.context).load(url).into(this)

fun AppCompatActivity.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/** model to entity mappers **/
fun MovieResponse.toEntity() = with(this) {
    MovieEntity(
            id = id.toInt(),
            title = title,
            posterPath = "$IMAGE_ENDPOINT${posterPath}",
            originalTitle = originalTitle,
            voteAverage = vote
    )
}

//  response to db entity
fun MovieResponse.toDbEntity() = with(this) {
    MovieEntity(
            id = id.toInt(),
            title = title,
            originalTitle = originalTitle,
            voteAverage = vote,
            posterPath = posterPath
    )
}

fun MovieDetailResponse.toDbEntity() = with(this){
    MovieEntity(
            id = id,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            backDropPath = backdropPath,
            genres = genres,
            voteAverage = voteAverage.toFloat()
    )
}

// db entity to usecase entity
fun MovieEntity.toUseCaseEntity() = with(this){
    Movie(
      id, originalTitle, IMAGE_ENDPOINT + posterPath, title, overview ?: "", genres.joinToString(), voteAverage
    )
}


