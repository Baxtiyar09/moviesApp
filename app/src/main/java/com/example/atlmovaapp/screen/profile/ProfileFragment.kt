package com.example.atlmovaapp.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.FragmentProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.delete_alert_dialog, null)
        val alertDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogView)
            .create()

        val titleTextView = dialogView.findViewById<TextView>(R.id.tvTitle)
        val messageTextView = dialogView.findViewById<TextView>(R.id.tvMessage)
        val noButton = dialogView.findViewById<TextView>(R.id.btnNo)
        val yesButton = dialogView.findViewById<TextView>(R.id.btnYes)


        titleTextView.text = "Log out"
        messageTextView.text = "Are you sure you want to log out?"

        noButton.setOnClickListener {
            alertDialog.dismiss()
        }
        yesButton.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSignInFragment())
            alertDialog.dismiss()
        }
        alertDialog.show()

    }
}