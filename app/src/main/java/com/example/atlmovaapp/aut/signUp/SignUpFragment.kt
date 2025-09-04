package com.example.atlmovaapp.aut.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.databinding.FragmentSignUpBinding
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        translation()

        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.signUpButton.isEnabled = isChecked
        }
        binding.checkBox.isChecked = false

        binding.signUpButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signUp(email, password)
            } else {
                binding.emailInput.error = "Email is required"
                binding.passwordInput.error = "Password is required"
            }
        }



    }


    fun observeData() {
        viewModel.isSucces.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                Toast.makeText(context, "Ugurlu qeydiyat", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.SignInKecid.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }
    }
}