package ru.zmeytee.skillpreview.repositories

import ru.zmeytee.skillpreview.data.models.User
import ru.zmeytee.skillpreview.networking.Api
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: Api) {

    suspend fun getAllUsers(): List<User> {
        return api.getAllUsers()
    }
}