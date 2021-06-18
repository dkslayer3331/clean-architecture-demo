package com.dazai.movieappwithcleanarch.data.utils

import com.dazai.movieappwithcleanarch.app.utils.*

sealed class ErrorEntity(
     var message : String
) {

    object ServiceUnavailable : ErrorEntity(ERROR_MESSAGE_SERVICE_NOT_AVAILABLE)

    object NotFound : ErrorEntity(ERROR_MESSAGE_404)

    data class Unknown(val localisedMessage : String) : ErrorEntity(localisedMessage)

    object ServerError : ErrorEntity(ERROR_MESSAGE_503)

    object NO_CONNECTION : ErrorEntity(ERROR_MESSAGE_NO_INTERNET_CONNECTION)

}