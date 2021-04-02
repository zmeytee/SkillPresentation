package ru.zmeytee.networkingsample.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Company(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null
)