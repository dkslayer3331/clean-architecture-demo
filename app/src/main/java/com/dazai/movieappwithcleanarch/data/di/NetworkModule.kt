package com.dazai.movieappwithcleanarch.data.di

import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.data.utils.TokenInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client : OkHttpClient) : MovieApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }

}