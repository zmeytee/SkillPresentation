package ru.zmeytee.skillpreview.networking

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.zmeytee.skillpreview.data.models.User

interface Api {

    @GET("/users")
    suspend fun getAllSimpleUsers(): List<User.SimpleUser>

    @GET("users/{id}")
    suspend fun getAdvancedUser(
        @Path("id") id: Long
    ): User.AdvancedUser
}