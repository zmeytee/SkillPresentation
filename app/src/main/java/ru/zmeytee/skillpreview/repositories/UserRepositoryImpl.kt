package ru.zmeytee.skillpreview.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zmeytee.skillpreview.data.models.User
import ru.zmeytee.skillpreview.networking.Api
import ru.zmeytee.skillpreview.repositories.interfaces.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: Api,
    private val defaultDispatcher: CoroutineDispatcher
) : UserRepository {

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