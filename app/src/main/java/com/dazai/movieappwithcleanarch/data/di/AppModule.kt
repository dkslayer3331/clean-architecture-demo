package com.dazai.movieappwithcleanarch.data.di

import com.dazai.movieappwithcleanarch.data.db.LocalDataSource
import com.dazai.movieappwithcleanarch.data.db.LocalDataSourceImpl
import com.dazai.movieappwithcleanarch.data.network.NetworkDataSource
import com.dazai.movieappwithcleanarch.data.network.NetworkDataSourceImpl
import com.dazai.movieappwithcleanarch.data.repositories.MovieRepositoryImpl
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.data.utils.ErrorHandlerImpl
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.domain.usecases.*
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
    abstract fun bindErrorHandler(impl : ErrorHandlerImpl) : ErrorHandler

    @Binds
    abstract fun bindGetMovieListUseCase(impl : GetMovieListUseCaseImpl) : GetMovieListUseCase

    @Binds
    abstract fun bindGetMovieDetailUseCase(impl : GetMovieDetailUseCaseImpl) : GetMovieDetailUseCase

    @Binds
    abstract fun bindErrorUseCase(impl : ErrorUseCaseImpl) : ErrorUseCase

    @Binds
    abstract fun bindNetworkSource(impl : NetworkDataSourceImpl) : NetworkDataSource

    @Binds
    abstract fun bindLocalSource(impl : LocalDataSourceImpl) : LocalDataSource

}