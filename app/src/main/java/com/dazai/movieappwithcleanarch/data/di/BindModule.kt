package com.dazai.movieappwithcleanarch.data.di

import com.dazai.movieappwithcleanarch.data.repositories.MovieRepositoryImpl
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieUseCase(impl : MovieUseCaseImpl) : MovieUseCase

}