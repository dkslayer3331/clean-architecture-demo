package com.dazai.movieappwithcleanarch

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun String.fullImageUrl() = "https://image.tmdb.org/t/p/original/$this"

fun ImageView.showImage(url : String) = Glide.with(this.context).load(url).into(this)

fun Context.showToast(message : String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()