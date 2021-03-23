package ru.zmeytee.skillpreview.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.zmeytee.skillpreview.R
import ru.zmeytee.skillpreview.data.adapters.UserAdapter
import ru.zmeytee.skillpreview.databinding.FragmentUsersBinding

@AndroidEntryPoint
class UsersFragment: Fragment(R.layout.fragment_users) {

    private val viewModel by viewModels<UsersViewModel>()
    private val binding by viewBinding(FragmentUsersBinding::bind)
    private var userAdapter: UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        initUsersList()

        binding.usersTitleTextView.text = "Users"
    }

    private fun bindViewModel() {
        with(viewModel) {
            users
                .onEach { userAdapter?.items = it }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun initUsersList() {
        userAdapter = UserAdapter {
            Snackbar.make(binding.root, "User id = $it", Snackbar.LENGTH_SHORT)
                .setAnchorView(R.id.appDescriptionFab)
                .show()
        }

        with(binding.usersList) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.getListOfAllUsers()
    }
}