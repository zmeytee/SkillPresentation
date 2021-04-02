package ru.zmeytee.networkingsample.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geo(
    val lat: Double? = null,
    val lng: Double? = null
)