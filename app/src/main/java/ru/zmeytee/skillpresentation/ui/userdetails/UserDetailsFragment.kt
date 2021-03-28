package ru.zmeytee.skillpresentation.ui.userdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.zmeytee.skillpresentation.R
import ru.zmeytee.skillpresentation.data.enums.ItemAction
import ru.zmeytee.skillpresentation.data.models.User
import ru.zmeytee.skillpresentation.databinding.FragmentUserDetailsBinding
import ru.zmeytee.skillpresentation.ui.FabActionListener

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private val viewModel by viewModels<UserDetailsViewModel>()
    private val binding by viewBinding(FragmentUserDetailsBinding::bind)
    private val args by navArgs<UserDetailsFragmentArgs>()
    private val fabActionListener: FabActionListener?
        get() = activity?.let { it as FabActionListener }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabActionListener?.setFabAction(ItemAction.BACK)
        bindViewModel()
        setListeners()
        getUserDetails(args.userId)
    }

    private fun bindViewModel() {
        with(viewModel) {
            isLoading
                .onEach { showLoading(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            currentUser
                .onEach { handleUserDetails(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun setListeners() {
        with(binding) {

        }
    }

    private fun getUserDetails(id: Long) {
        viewModel.getUser(id)
    }

    private fun handleUserDetails(user: User?) {
        if (user == null) return

        when (user) {
            is User.Remote -> showUserDetails(user)
            else -> { }
        }
    }

    private fun showUserDetails(remoteUser: User.Remote) {
        with(binding) {
            userDetailsCard.userItemUserName.text = remoteUser.userName
            userDetailsCard.userItemName.text = remoteUser.name

            userDetailsEmail.text = remoteUser.email
            userDetailsWebsite.text = remoteUser.website
            userDetailsPhone.text = remoteUser.phone
            userDetailsAddress.text = remoteUser.address?.street
            userDetailsAddressDescription.text = remoteUser.address?.city
            userDetailsCompany.text = remoteUser.company?.name

            userDetailsCard.userItemAvatar.load("https://www.meme-arsenal.com/memes/ad998282fd526298aeb217a8e2ee02b0.jpg") {
                placeholder(R.drawable.ic_person)
                transformations(CircleCropTransformation())
            }
        }
    }

    private fun showLoading(show: Boolean) {
        binding.userDetailsLoading.root.isVisible = show
    }
}