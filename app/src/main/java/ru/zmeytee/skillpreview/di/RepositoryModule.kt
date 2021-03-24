package ru.zmeytee.skillpreview.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.zmeytee.skillpreview.repositories.UserRepositoryImpl
import ru.zmeytee.skillpreview.repositories.interfaces.UserRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideUserRepository(impl: UserRepositoryImpl): UserRepository
}