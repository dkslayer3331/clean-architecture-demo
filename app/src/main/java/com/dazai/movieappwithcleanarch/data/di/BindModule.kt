package com.dazai.movieappwithcleanarch.data.di

import com.dazai.movieappwithcleanarch.data.repositories.MovieRepositoryImpl
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    abstract fun bindMovieUsecase(impl : MovieUsecaseImpl) : MovieUseCase

}