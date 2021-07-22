package com.dazai.movieappwithcleanarch.ui.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity
import com.dazai.movieappwithcleanarch.data.responses.MovieResponse
import com.dazai.movieappwithcleanarch.domain.model.Movie

fun ImageView.showImage(url: String) = Glide.with(this.context).load(url).into(this)

fun AppCompatActivity.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


//for testing



