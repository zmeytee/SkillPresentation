package ru.zmeytee.skillpreview.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class User {

    @JsonClass(generateAdapter = true)
    data class SimpleUser(
        val id: Long,
        val name: String,
        @Json(name = "username") val userName: String
    ): User()

    @JsonClass(generateAdapter = true)
    data class AdvancedUser(
        val id: Long,
        val name: String,
        val username: String,
        val email: String,
        val address: Address,
        val phone: String,
        val website: String,
        val company: Company
    ): User()
}