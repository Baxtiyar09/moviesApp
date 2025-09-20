package com.example.atlmovaapp.screen.profile.join_Premium.subscribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.FragmentSubscribePremiumBinding

class SubscribePremiumFragment : Fragment() {
    private lateinit var binding: FragmentSubscribePremiumBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscribePremiumBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.cardPremiumInfo1.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_subscribePremiumFragment_to_selectCardFragment)

        }
        binding.cardPremiumInfo2.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_subscribePremiumFragment_to_selectCardFragment)
        }


        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}