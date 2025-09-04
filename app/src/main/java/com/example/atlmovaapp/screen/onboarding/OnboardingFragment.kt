package com.example.atlmovaapp.screen.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.adapter.OnboardingPagerAdapter
import com.example.atlmovaapp.databinding.FragmentOnboardingBinding
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
        val list = arrayListOf<String>(
            "Welcome to Mova"
        )
        list.add("We are here to help you")
        list.add("aecpmwcmpwmepmcwpmo")


        binding.imageView2.setImageResource(R.drawable.movaonboardinimage)

        adapter.updateList(list)
        binding.buttonGetStarted.setOnClickListener {
            val navController = findNavController()
            navController.navigate(OnboardingFragmentDirections.actionOnboardingFragmentToWelcomeLoginFragment())
        }



    }
}