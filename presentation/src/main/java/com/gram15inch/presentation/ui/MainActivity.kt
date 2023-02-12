package com.gram15inch.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.gram15inch.presentation.viewmodel.MainViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ActivityMainBinding
import com.gram15inch.presentation.MyCoupangEatsApplication
import com.gram15inch.presentation.MyCoupangEatsApplication.Companion.X_ACCESS_TOKEN
import com.gram15inch.presentation.base.DataBindingActivity
import com.gram15inch.presentation.ui.dialog.LoginDialogFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity<ActivityMainBinding>(
    R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        navHostFragment = binding.mainNavHostFragment.getFragment()
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(binding.mainBottomNav.menu)
        binding.mainBottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    backstackClear()
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.searchFragment -> {
                    backstackClear()
                    navController.navigate(R.id.searchFragment)
                    true
                }
                R.id.bookmarkFragment -> {
                    backstackClear()
                    navController.navigate(R.id.bookmarkFragment)
                    true
                }
                R.id.historyFragment -> {
                    backstackClear()
                    MyCoupangEatsApplication.prefs.getString(X_ACCESS_TOKEN,"").also {
                        if(it!="")
                            navController.navigate(R.id.historyFragment)
                        else {
                            LoginDialogFragment().show(
                                supportFragmentManager,
                                "LoginDialog"
                            )
                        }
                    }
                    false
                }
                R.id.eatsFragment -> {
                    backstackClear()
                    MyCoupangEatsApplication.prefs.getString(X_ACCESS_TOKEN,"").also {
                        if(it!="")
                            navController.navigate(R.id.eatsFragment)
                        else
                            LoginDialogFragment().show(supportFragmentManager,
                                "LoginDialog")
                    }
                    false
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun backstackClear(){
        navController.popBackStack(R.id.homeFragment,true)
        navController.popBackStack(R.id.bookmarkFragment,true)
        navController.popBackStack(R.id.searchFragment,true)
        navController.popBackStack(R.id.historyFragment,true)
        navController.popBackStack(R.id.eatsFragment,true)
    }
}