package com.dazai.movieappwithcleanarch.app

import android.app.Application
import androidx.room.Room
import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}