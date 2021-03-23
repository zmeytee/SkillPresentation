package ru.zmeytee.skillpreview.ui.users

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.zmeytee.skillpreview.R
import ru.zmeytee.skillpreview.repositories.UserRepository
import ru.zmeytee.skillpreview.factories.UsersViewModelFactory
import ru.zmeytee.skillpreview.networking.Networking

@AndroidEntryPoint
class UsersFragment: Fragment(R.layout.fragment_users) {

    private val viewModel by viewModels<UsersViewModel>()
}