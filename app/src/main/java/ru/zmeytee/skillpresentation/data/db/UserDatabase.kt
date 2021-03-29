package ru.zmeytee.skillpresentation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zmeytee.skillpresentation.data.db.UserDatabase.Companion.DB_VERSION
import ru.zmeytee.skillpresentation.data.models.User

@Database(
    entities = [
        User::class
    ],
    version = DB_VERSION
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "skill-presentation-database"
        const val DB_VERSION = 1
    }
}