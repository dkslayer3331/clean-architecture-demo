package com.dazai.movieappwithcleanarch.data.utils

import com.dazai.movieappwithcleanarch.app.utils.ERROR_MESSAGE_404
import com.dazai.movieappwithcleanarch.app.utils.ERROR_MESSAGE_503
import com.dazai.movieappwithcleanarch.app.utils.ERROR_MESSAGE_SERVICE_NOT_AVAILABLE
import com.dazai.movieappwithcleanarch.app.utils.ERROR_MESSAGE_UNKNOWN

sealed class ErrorEntity(
     val message : String
) {

    object ServiceUnavailable : ErrorEntity(ERROR_MESSAGE_SERVICE_NOT_AVAILABLE)

    object NotFound : ErrorEntity(ERROR_MESSAGE_404)

    object Unknown : ErrorEntity(ERROR_MESSAGE_UNKNOWN)

    object ServerError : ErrorEntity(ERROR_MESSAGE_503)

}