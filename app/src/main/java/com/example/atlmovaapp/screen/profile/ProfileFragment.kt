package com.example.atlmovaapp.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.premiumLayout.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToSubscribePremiumFragment()
            findNavController().navigate(action)
        }


        binding.EditProfileView.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
        }

        binding.NoficationView.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToNotficationFragment())
        }

        binding.DownloadView.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToProfileDownloadFragment())
        }

        binding.SecurityView.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSecurityFragment())
        }

        binding.LanguageView.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLanguageFragment())
        }

        binding.HelpCenterView.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToHelpCenterFragment())
        }

        binding.PrivacyPolicyView.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToPrivacyPolicyFragment())
        }

        binding.logAutIcon.setOnClickListener {
            alertDelete()
        }


    }


    fun alertDelete() {
        val bottomSheet = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.log_aut_dialog, null)
        bottomSheet.setContentView(bottomSheetView)

        val butonCancel = bottomSheetView.findViewById<View>(R.id.btnCancel)
        val btnLogout = bottomSheetView.findViewById<View>(R.id.btnLogout)

        butonCancel.setOnClickListener {
            bottomSheet.dismiss()
        }
        btnLogout.setOnClickListener {
            bottomSheet.dismiss()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSignInFragment())

        }
        bottomSheet.show()

    }
}