package com.example.atlmovaapp.screen.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUserLogin()
    }

    private fun checkUserLogin() {
        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
        val isLogin = sp.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000)
            val navController = findNavController()
            if (navController.currentDestination?.id == R.id.splashFragment) {
                if (isLogin) {
                    navController.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                } else {
                    navController.navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
                }
            }
        }
    }
}