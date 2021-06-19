package com.dazai.movieappwithcleanarch.data.utils

import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable){
            is UnknownHostException -> ErrorEntity.NoConnection
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.ServerError
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                    else -> ErrorEntity.Unknown(throwable.localizedMessage)
                }
            }
            else -> ErrorEntity.Unknown(throwable.localizedMessage)
        }

    }
}