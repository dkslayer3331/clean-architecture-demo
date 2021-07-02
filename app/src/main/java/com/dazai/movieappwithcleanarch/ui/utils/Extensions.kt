package com.dazai.movieappwithcleanarch.ui.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity
import com.dazai.movieappwithcleanarch.data.responses.GenreResponse
import com.dazai.movieappwithcleanarch.data.responses.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.responses.MovieResponse
import com.dazai.movieappwithcleanarch.domain.model.Movie

fun ImageView.showImage(url: String) = Glide.with(this.context).load(url).into(this)

fun AppCompatActivity.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/** response to db entity **/

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
            voteAverage = voteAverage.toFloat(),
            releaseDate = releaseDate
    )
}

/** db entity to use case models **/
fun MovieEntity.toUseCaseEntity() = with(this){
    Movie(
      id, originalTitle, IMAGE_ENDPOINT + posterPath, title, overview ?: "",
            genres?.joinToString { it.name } ?: "", voteAverage, releaseDate ?: ""
    )
}

//for testing
fun MovieResponse.mockObj() = with(this){
    MovieResponse(0L, "", "", "", 0F)
}

fun mockMovieResponse() = MovieResponse(0L, "", "", "", 0F)

fun mockMovie() = Movie(0, "","", "", "" , "", 0.0f, "")

fun mockMovieEntity(movieResponse: MovieResponse) = MovieEntity(id = movieResponse.id.toInt(),
    title = movieResponse.title, originalTitle = movieResponse.originalTitle, voteAverage = movieResponse.vote, posterPath = movieResponse.posterPath)


