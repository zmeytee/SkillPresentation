package ru.zmeytee.networkingsample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.zmeytee.networkingsample.data.repositories.UserRepositoryImpl
import ru.zmeytee.networkingsample.data.repositories.interfaces.UserRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesUserRepository(impl: UserRepositoryImpl): UserRepository
}