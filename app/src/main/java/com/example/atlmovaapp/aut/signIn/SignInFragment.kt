package com.example.atlmovaapp.aut.signIn

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.databinding.FragmentSignInBinding
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoginUser()
        observeData()
        translation()

        binding.signInbutton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                binding.emailInput.error = "Email is required"
                binding.passwordInput.error = "Password is required"

            }
        }


    }

    fun observeData() {
        viewModel.isSucces.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
            }
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.animationView.visible()
            } else {
                binding.animationView.gone()
            }
        }
    }

    fun translation() {
        binding.signUpKecid.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setLoginUser() {
        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
        sp.edit().putBoolean("isLogin", true).apply()
    }
}