package ru.zmeytee.skillpreview.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zmeytee.skillpreview.data.models.User
import ru.zmeytee.skillpreview.networking.Api
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: Api, private val defaultDispatcher: CoroutineDispatcher) {

    suspend fun getAllUsers(): List<User> {
        return withContext(defaultDispatcher) {
            api.getAllSimpleUsers()
        }
    }

    suspend fun getUser(id: Long): User {
        return withContext(defaultDispatcher) {
            api.getAdvancedUser(id)
        }
    }
}