package ru.zmeytee.skillpresentation.ui.useradd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.zmeytee.skillpresentation.data.models.User
import ru.zmeytee.skillpresentation.data.repositories.interfaces.UserRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserAddViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun saveUser(user: User.Remote) {
        viewModelScope.launch {
            Timber.d(user.toString())
            repository.saveUser(user)
        }
    }
}