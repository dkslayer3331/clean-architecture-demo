package com.dazai.movieappwithcleanarch.data.di

import android.content.Context
import androidx.room.Room
import com.dazai.movieappwithcleanarch.data.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : AppDb {
        return Room.databaseBuilder(context, AppDb::class.java, "APP_DB" )
                .fallbackToDestructiveMigration()
                .build()
    }

}