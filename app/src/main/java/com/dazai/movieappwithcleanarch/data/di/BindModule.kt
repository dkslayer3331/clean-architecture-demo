package com.dazai.movieappwithcleanarch.data.di

import com.dazai.movieappwithcleanarch.data.repositories.MovieRepositoryImpl
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.data.utils.ErrorHandlerImpl
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieUseCase(impl : MovieUseCaseImpl) : MovieUseCase

    @Binds
    abstract fun bindErrorHandler(impl : ErrorHandlerImpl) : ErrorHandler

}