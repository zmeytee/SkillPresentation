package ru.zmeytee.skillpresentation.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zmeytee.skillpresentation.data.contracts.DbContracts
import ru.zmeytee.skillpresentation.data.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM ${DbContracts.User.TABLE_NAME}")
    fun getAllUsersFlow(): Flow<List<User>>

    @Query("SELECT * FROM ${DbContracts.User.TABLE_NAME}")
    suspend fun getAllUsersList(): List<User>

    @Query("SELECT * FROM ${DbContracts.User.TABLE_NAME} WHERE ${DbContracts.User.ID} = :id")
    suspend fun getUserById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE FROM ${DbContracts.User.TABLE_NAME} WHERE ${DbContracts.User.ID} = :userId")
    suspend fun deleteUserById(userId: Long)
}