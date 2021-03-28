package ru.zmeytee.skillpresentation.ui.useradd

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import ru.zmeytee.skillpresentation.R
import ru.zmeytee.skillpresentation.data.enums.ItemAction
import ru.zmeytee.skillpresentation.data.models.Address
import ru.zmeytee.skillpresentation.data.models.Company
import ru.zmeytee.skillpresentation.data.models.Geo
import ru.zmeytee.skillpresentation.data.models.User
import ru.zmeytee.skillpresentation.databinding.FragmentUserAddBinding
import ru.zmeytee.skillpresentation.ui.FabActionListener

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

        }
    }

    private fun setListeners() {
        binding.saveUserFab.setOnClickListener { saveUser(getUserFromForms()) }
    }

    private fun saveUser(user: User.Remote) {
        viewModel.saveUser(user)
    }

    private fun getUserFromForms(): User.Remote {
        val name = binding.nameEditText.text.toString()
        val userName = binding.userNameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val phone = binding.phoneEditText.text.toString()
        val website = binding.websiteEditText.text.toString()

        return User.Remote(
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

    private fun getAddressFromForms(): Address.Remote {
        val addressStreet = binding.streetEditText.text.toString()
        val addressSuite = binding.suiteEditText.text.toString()
        val addressCity = binding.cityEditText.text.toString()
        val addressZipcode = binding.zipcodeEditText.text.toString()

        return Address.Remote(
            street = addressStreet,
            suite = addressSuite,
            city = addressCity,
            zipcode = addressZipcode,
            geo = getGeoFromForms()
        )
    }

    private fun getGeoFromForms(): Geo.Remote {
        val geoLat = null
        val geoLng = null

        return Geo.Remote(
            lat = geoLat,
            lng = geoLng
        )
    }

    private fun getCompanyFromForms(): Company.Remote {
        val companyName = binding.companyNameEditText.text.toString()
        val companyCatchPhrase = binding.companyCatchPhraseEditText.text.toString()
        val companyBs = binding.companyBsEditText.text.toString()

        return Company.Remote(
            name = companyName,
            catchPhrase = companyCatchPhrase,
            bs = companyBs
        )
    }
}