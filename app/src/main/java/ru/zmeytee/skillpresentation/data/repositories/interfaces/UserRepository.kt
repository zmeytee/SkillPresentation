package ru.zmeytee.skillpresentation.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import ru.zmeytee.skillpresentation.data.models.User

interface UserRepository {

    fun getAllLocalUsersFlow(): Flow<List<User>>
    suspend fun getAllUsers(): List<User>
    suspend fun getUser(id: Long): User
    suspend fun saveUser(user: User)
    suspend fun deleteUser(id: Long)
}