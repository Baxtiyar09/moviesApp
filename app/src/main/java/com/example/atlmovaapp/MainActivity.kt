package com.example.atlmovaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.atlmovaapp.databinding.ActivityMainBinding
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setToolbar()
        setBottomBar()
    }


    private fun setBottomBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? NavHostFragment
                ?: return

        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )


    }


    private fun setToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomMenu = binding.bottomNavigationView

        bottomMenu.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> bottomMenu.gone()
                R.id.signInFragment -> bottomMenu.gone()
                R.id.signUpFragment -> bottomMenu.gone()
                R.id.onboardingFragment -> bottomMenu.gone()
                R.id.welcomeLoginFragment -> bottomMenu.gone()
                R.id.detailFragment -> bottomMenu.gone()
                R.id.editProfileFragment -> bottomMenu.gone()
                R.id.notficationFragment -> bottomMenu.gone()
                R.id.profileDownloadFragment -> bottomMenu.gone()
                R.id.securityFragment -> bottomMenu.gone()
                R.id.languageFragment -> bottomMenu.gone()
                R.id.helpCenterFragment -> bottomMenu.gone()
                R.id.privacyPolicyFragment -> bottomMenu.gone()
                R.id.selectCardFragment -> bottomMenu.gone()
                R.id.addNewCardFragment -> bottomMenu.gone()
                R.id.reviewSummaryCardFragment -> bottomMenu.gone()
                R.id.subscribePremiumFragment -> bottomMenu.gone()


                else -> bottomMenu.visible()
            }
        }
    }

}