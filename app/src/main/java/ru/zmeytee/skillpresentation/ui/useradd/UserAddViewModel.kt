package ru.zmeytee.skillpresentation.ui.useradd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zmeytee.skillpresentation.data.models.User
import ru.zmeytee.skillpresentation.data.repositories.interfaces.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserAddViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _addingSuccess = MutableStateFlow(false)

    val isLoading = _isLoading.asStateFlow()
    val addingSuccess = _addingSuccess.asStateFlow()

    fun saveUser(user: User) {
        viewModelScope.launch {
            repository.saveUser(user)
            _addingSuccess.value = true
        }
    }
}