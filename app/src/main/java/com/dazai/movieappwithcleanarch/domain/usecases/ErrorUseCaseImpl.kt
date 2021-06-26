package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import javax.inject.Inject

class ErrorUseCaseImpl @Inject constructor(
    private val errorHandler : ErrorHandler
) : ErrorUseCase{
    override fun getErrorMessage(e: Exception) = errorHandler.getError(e.fillInStackTrace()).message
}