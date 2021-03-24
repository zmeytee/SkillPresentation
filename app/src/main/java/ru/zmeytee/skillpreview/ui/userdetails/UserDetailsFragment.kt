package ru.zmeytee.skillpreview.ui.userdetails

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.zmeytee.skillpreview.R
import ru.zmeytee.skillpreview.data.models.User
import ru.zmeytee.skillpreview.databinding.FragmentUserDetailsBinding

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private val viewModel by viewModels<UserDetailsViewModel>()
    private val binding by viewBinding(FragmentUserDetailsBinding::bind)
    private val args by navArgs<UserDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setListeners()
        getUserDetails(args.userId)
        changeItemAction()
    }

    private fun bindViewModel() {
        with(viewModel) {
            currentUser
                .onEach { handleUserDetails(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun setListeners() {
        with(binding) {
            userDetailsCard.userItemNavAction.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun changeItemAction() {
        binding.userDetailsCard.userItemNavAction
            .setImageDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_close, null)
            )
    }

    private fun getUserDetails(id: Long) {
        viewModel.getUser(id)
    }

    private fun handleUserDetails(user: User?) {
        if (user == null) return

        when (user) {
            is User.AdvancedUser -> {
                showAdvancedUserDetails(user)
            }
            else -> {
            }
        }
    }

    private fun showAdvancedUserDetails(advancedUser: User.AdvancedUser) {
        with(binding) {
            val address = advancedUser.address.let { it.city + it.street }
            val company = advancedUser.company.let { "${it.name}\n<${it.catchPhrase}>" }

            userDetailsCard.userItemUserName.text = advancedUser.username
            userDetailsCard.userItemName.text = advancedUser.name

            userDetailsEmail.text = advancedUser.email
            userDetailsWebsite.text = advancedUser.website
            userDetailsPhone.text = advancedUser.phone
            userDetailsAddress.text = address
            userDetailsCompany.text = company
        }
    }
}