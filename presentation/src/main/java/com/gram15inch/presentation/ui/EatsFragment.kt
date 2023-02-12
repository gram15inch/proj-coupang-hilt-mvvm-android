package com.gram15inch.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController

import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.FragmentEatsBinding
import com.gram15inch.presentation.MyCoupangEatsApplication
import com.gram15inch.presentation.MyCoupangEatsApplication.Companion.X_ACCESS_TOKEN
import com.gram15inch.presentation.base.DataBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EatsFragment : DataBindingFragment<FragmentEatsBinding>(R.layout.fragment_eats) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myEatsLogoutBtn.setOnClickListener {
            MyCoupangEatsApplication.prefs.setString(X_ACCESS_TOKEN,"")
            findNavController().popBackStack(R.id.eatsFragment,true)
            findNavController().navigate(R.id.homeFragment)
        }
        binding.myEatsJwtTxt.text = MyCoupangEatsApplication.prefs.getString(X_ACCESS_TOKEN,"")
    }
}