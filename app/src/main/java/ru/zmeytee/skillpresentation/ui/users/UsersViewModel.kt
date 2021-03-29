package ru.zmeytee.skillpresentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zmeytee.skillpresentation.data.repositories.UserRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepositoryImpl
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val isLoading = _isLoading.asStateFlow()
    val users = repository.getAllLocalUsersFlow()

    init {
        viewModelScope.launch {
            repository.getAllUsers()
        }
    }

    private fun getListOfAllUsers() {
        viewModelScope.launch {
            _isLoading.value = true
//            repository.getAllUsers()
            _isLoading.value = false
        }
    }
}