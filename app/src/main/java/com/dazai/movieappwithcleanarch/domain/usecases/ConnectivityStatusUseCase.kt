package com.dazai.movieappwithcleanarch.domain.usecases

interface ConnectivityStatusUseCase {
    fun showNoConnectionMessage()
    fun showConnectedMessage()
}