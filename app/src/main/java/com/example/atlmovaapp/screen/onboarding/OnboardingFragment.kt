package com.example.atlmovaapp.screen.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.adapter.OnboardingPagerAdapter
import com.example.atlmovaapp.databinding.FragmentOnboardingBinding
import com.example.atlmovaapp.model.onboarding.OnboardinPagerModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private val adapter = OnboardingPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.viewPager)

        val list = listOf(

            OnboardinPagerModel(
                "Welcome to Mova",
                "The best movie streaming app of the century to make your days great!"

            ),
            OnboardinPagerModel(
                "Discover Thousands of Movies",
                "From timeless classics to the latest blockbusters — everything is just a tap away!"
            ),
            OnboardinPagerModel(
                "Watch Anytime Anywhere",
                "Enjoy seamless streaming on your phone, tablet, or TV entertainment in your pocket."
            ),
        )


        binding.imageView2.setImageResource(R.drawable.movaonboardinimage)

        adapter.updateList(list)
        binding.buttonGetStarted.setOnClickListener {
            val navController = findNavController()
            navController.navigate(OnboardingFragmentDirections.actionOnboardingFragmentToWelcomeLoginFragment())
        }


    }
}