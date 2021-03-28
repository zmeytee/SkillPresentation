package ru.zmeytee.skillpresentation.data.networking

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.zmeytee.skillpresentation.data.models.User

interface Api {

    @GET("users")
    suspend fun getAllUsers(): List<User.Remote>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): User.Remote

    @POST("users")
    suspend fun saveUser(
        @Body user: User.Remote
    ): User.Remote
}