package ru.zmeytee.skillpresentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zmeytee.skillpresentation.data.models.User
import ru.zmeytee.skillpresentation.data.repositories.UserRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepositoryImpl
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _users = MutableStateFlow<List<User>>(emptyList())

    val isLoading = _isLoading.asStateFlow()
    val users = _users.asStateFlow()

    init {
        getListOfAllUsers()
    }

    private fun getListOfAllUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000) // Для наглядности
            _users.value = repository.getAllUsers()
            _isLoading.value = false
        }
    }
}