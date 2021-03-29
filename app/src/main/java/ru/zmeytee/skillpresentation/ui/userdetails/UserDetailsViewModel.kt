package ru.zmeytee.skillpresentation.ui.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zmeytee.skillpresentation.data.models.User
import ru.zmeytee.skillpresentation.data.repositories.UserRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: UserRepositoryImpl
) : ViewModel() {

    private var currentJob: Job? = null

    private val _isLoading = MutableStateFlow(false)
    private val _deletingSuccess = MutableStateFlow(false)
    private val _currentUser = MutableStateFlow<User?>(null)

    val isLoading = _isLoading.asStateFlow()
    val deletingSuccess = _deletingSuccess.asStateFlow()
    val currentUser = _currentUser.asStateFlow()

    fun getUser(id:Long) {
        currentJob = viewModelScope.launch {
            _isLoading.value = true
            _currentUser.value = repository.getUser(id)
            _isLoading.value = false
        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            repository.deleteUser(id)
            _deletingSuccess.value = true
        }
    }

    override fun onCleared() {
        currentJob?.cancelChildren()
        super.onCleared()
    }
}