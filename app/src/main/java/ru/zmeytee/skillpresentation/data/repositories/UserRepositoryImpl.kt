package ru.zmeytee.skillpresentation.data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.zmeytee.skillpresentation.data.db.UserDao
import ru.zmeytee.skillpresentation.data.models.User
import ru.zmeytee.skillpresentation.data.networking.Api
import ru.zmeytee.skillpresentation.data.repositories.interfaces.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: Api,
    private val userDao: UserDao,
    private val defaultDispatcher: CoroutineDispatcher
) : UserRepository {

    override fun getAllLocalUsersFlow(): Flow<List<User>> {
        return userDao.getAllUsersFlow()
    }

    override suspend fun getAllUsers(): List<User> {
        return withContext(defaultDispatcher) {
            val list = getAllRemoteUsers()
            saveLocalUsers(list)
            list
        }
    }

    override suspend fun getUser(id: Long): User {
        return withContext(defaultDispatcher) {
            getLocalUser(id)
        }
    }

    override suspend fun saveUser(user: User) {
        withContext(defaultDispatcher) {
            val newUser = saveRemoteUser(user)
            saveLocalUsers(listOf(newUser))
        }
    }

    override suspend fun deleteUser(id: Long) {
        withContext(defaultDispatcher) {
            deleteRemoteUser(id)
            deleteLocalUser(id)
        }
    }

//====== Работа с сетью ===========================================================

    private suspend fun getAllRemoteUsers(): List<User> {
        return api.getAllUsers()
    }

    private suspend fun getRemoteUser(id: Long): User {
        return api.getUserById(id)
    }

    private suspend fun saveRemoteUser(user: User): User {
        return api.saveUser(user)
    }

    private suspend fun deleteRemoteUser(userId: Long) {
        api.deleteUserById(userId)
    }

//======== Работа с БД ============================================================

    private suspend fun getAllLocalUsersList(): List<User> {
        return userDao.getAllUsersList()
    }

    private suspend fun getLocalUser(id: Long): User {
        return userDao.getUserById(id)
    }

    private suspend fun saveLocalUsers(users: List<User>) {
        userDao.insertUsers(users)
    }

    private suspend fun deleteLocalUser(userId: Long) {
        userDao.deleteUserById(userId)
    }
}