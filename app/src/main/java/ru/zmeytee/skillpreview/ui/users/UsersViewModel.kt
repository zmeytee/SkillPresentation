package ru.zmeytee.skillpreview.ui.users

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.zmeytee.skillpreview.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {


}