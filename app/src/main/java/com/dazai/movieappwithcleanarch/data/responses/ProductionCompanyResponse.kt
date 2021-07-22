package com.dazai.movieappwithcleanarch.data.responses
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompanyResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String
)