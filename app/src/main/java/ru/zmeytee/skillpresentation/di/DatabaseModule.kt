package ru.zmeytee.skillpresentation.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zmeytee.skillpresentation.data.db.UserDatabase
import ru.zmeytee.skillpresentation.data.db.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application,
            UserDatabase::class.java,
            UserDatabase.DB_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(db: UserDatabase): UserDao {
        return db.userDao()
    }
}