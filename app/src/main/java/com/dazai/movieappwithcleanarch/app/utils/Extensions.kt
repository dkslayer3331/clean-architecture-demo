package com.dazai.movieappwithcleanarch.app.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

fun ImageView.showImage(url : String) = Glide.with(this.context).load(url).into(this)

fun AppCompatActivity.showToast(message : String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()