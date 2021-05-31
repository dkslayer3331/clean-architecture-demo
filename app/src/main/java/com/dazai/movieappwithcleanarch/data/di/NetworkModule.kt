package com.dazai.movieappwithcleanarch.data.di

import android.app.Application
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.data.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor()).build()
    }

    @Provides
    fun provideRetrofit(client : OkHttpClient) : MovieApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }

}