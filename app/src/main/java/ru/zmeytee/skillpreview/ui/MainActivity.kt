package ru.zmeytee.skillpreview.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.zmeytee.skillpreview.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onBackPressed() {
        if (findNavController(R.id.navHostFragment).navigateUp().not()) super.onBackPressed()
    }
}