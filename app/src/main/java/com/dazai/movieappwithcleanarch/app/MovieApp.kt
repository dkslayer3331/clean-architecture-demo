package com.dazai.movieappwithcleanarch.app

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}