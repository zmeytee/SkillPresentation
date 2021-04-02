package ru.zmeytee.networkingsample.data.repositories.interfaces

import ru.zmeytee.networkingsample.data.models.User

interface UserRepository {

    suspend fun getAllUsers(): List<User>
    suspend fun getUser(id: Long): User
    suspend fun saveUser(user: User): User
    suspend fun deleteUser(id: Long)
}