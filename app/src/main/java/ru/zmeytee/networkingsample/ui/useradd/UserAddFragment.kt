package ru.zmeytee.networkingsample.ui.useradd

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.zmeytee.networkingsample.R
import ru.zmeytee.networkingsample.data.enums.ItemAction
import ru.zmeytee.networkingsample.data.models.Address
import ru.zmeytee.networkingsample.data.models.Company
import ru.zmeytee.networkingsample.data.models.Geo
import ru.zmeytee.networkingsample.data.models.User
import ru.zmeytee.networkingsample.databinding.FragmentUserAddBinding
import ru.zmeytee.networkingsample.ui.FabActionListener

@AndroidEntryPoint
class UserAddFragment : Fragment(R.layout.fragment_user_add) {

    private val viewModel by viewModels<UserAddViewModel>()
    private val binding by viewBinding(FragmentUserAddBinding::bind)
    private val fabActionListener: FabActionListener?
        get() = activity?.let { it as FabActionListener }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setListeners()
        fabActionListener?.setFabAction(ItemAction.BACK)

        binding.avatarImage.load("https://www.meme-arsenal.com/memes/ad998282fd526298aeb217a8e2ee02b0.jpg") {
            placeholder(R.drawable.ic_person)
            transformations(CircleCropTransformation())
        }
    }

    private fun bindViewModel() {
        with(viewModel) {
            isLoading
                .onEach { showLoading(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            addingSuccess
                .onEach { if (it) findNavController().navigateUp() }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun setListeners() {
        with(binding) {
            contactsTitleTextView.setOnClickListener {
                showHiddenForms(binding.contactsGroup, binding.contactsSpoiler)
            }
            addressTitleTextView.setOnClickListener {
                showHiddenForms(binding.addressGroup, binding.addressSpoiler)
            }
            companyTitleTextView.setOnClickListener {
                showHiddenForms(binding.companyGroup, binding.companySpoiler)
            }
            saveUserFab.setOnClickListener { saveUser(getUserFromForms()) }
        }
    }

    private fun showHiddenForms(view: View, imageView: ImageView) {
        val isVisible = view.isVisible
        val drawable = if (isVisible) {
            R.drawable.ic_arrow_right
        } else {
            R.drawable.ic_arrow_down
        }

        view.isVisible = isVisible.not()
        imageView.setImageDrawable(
            ResourcesCompat.getDrawable(resources, drawable, null)
        )
    }

    private fun saveUser(user: User) {
        viewModel.saveUser(user)
    }

    private fun getUserFromForms(): User {
        val name = binding.nameEditText.text.toString()
        val userName = binding.userNameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val phone = binding.phoneEditText.text.toString()
        val website = binding.websiteEditText.text.toString()

        return User(
            id = 0,
            name = name,
            userName = userName,
            email = email,
            address = getAddressFromForms(),
            phone = phone,
            website = website,
            company = getCompanyFromForms()
        )
    }

    private fun getAddressFromForms(): Address {
        val addressStreet = binding.streetEditText.text?.toString()
        val addressSuite = binding.suiteEditText.text.toString()
        val addressCity = binding.cityEditText.text.toString()
        val addressZipcode = binding.zipcodeEditText.text.toString()

        return Address(
            street = addressStreet,
            suite = addressSuite,
            city = addressCity,
            zipcode = addressZipcode,
            geo = getGeoFromForms()
        )
    }

    private fun getGeoFromForms(): Geo {
        val geoLat = null
        val geoLng = null

        return Geo(
            lat = geoLat,
            lng = geoLng
        )
    }

    private fun getCompanyFromForms(): Company {
        val companyName = binding.companyNameEditText.text.toString()
        val companyCatchPhrase = binding.companyCatchPhraseEditText.text.toString()
        val companyBs = binding.companyBsEditText.text.toString()

        return Company(
            name = companyName,
            catchPhrase = companyCatchPhrase,
            bs = companyBs
        )
    }

    private fun showLoading(show: Boolean) {
        binding.loadingProgress.root.isVisible = show
    }
}