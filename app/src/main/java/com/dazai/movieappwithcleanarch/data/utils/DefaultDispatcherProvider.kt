package com.dazai.movieappwithcleanarch.data.utils

import kotlinx.coroutines.Dispatchers

interface DispatcherProvider{
    fun io() = Dispatchers.IO
    fun main() = Dispatchers.Main
    fun default() = Dispatchers.Default
}

class DefaultDispatcherProvider : DispatcherProvider