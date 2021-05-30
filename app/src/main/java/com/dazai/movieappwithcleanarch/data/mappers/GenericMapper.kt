package com.dazai.movieappwithcleanarch.data.mappers

interface GenericMapper<T, V> {
    fun toEntity(model : V) : T
}