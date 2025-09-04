package com.example.atlmovaapp.screen.welcomeLogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.databinding.FragmentWelcomeLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeLoginFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.signInFacebookButton.setOnClickListener {
            findNavController().navigate(WelcomeLoginFragmentDirections.actionWelcomeLoginFragmentToSignInFragment())
        }
        binding.signInGoogleButton.setOnClickListener {
            findNavController().navigate(WelcomeLoginFragmentDirections.actionWelcomeLoginFragmentToSignInFragment())
        }
        binding.signInAppleButton.setOnClickListener {
            findNavController().navigate(WelcomeLoginFragmentDirections.actionWelcomeLoginFragmentToSignInFragment())
        }

        binding.SigninButton.setOnClickListener {
            findNavController().navigate(WelcomeLoginFragmentDirections.actionWelcomeLoginFragmentToSignInFragment())
        }
        binding.SignUptext.setOnClickListener {
            findNavController().navigate(WelcomeLoginFragmentDirections.actionWelcomeLoginFragmentToSignUpFragment())
        }

    }


}