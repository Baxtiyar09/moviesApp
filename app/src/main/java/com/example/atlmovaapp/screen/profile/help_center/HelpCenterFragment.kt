package com.example.atlmovaapp.screen.profile.help_center

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.adapter.HelpCenterAdapter
import com.example.atlmovaapp.adapter.HelpCenterContactAdapter
import com.example.atlmovaapp.databinding.FragmentHelpCenterBinding
import com.example.atlmovaapp.model.help_center.contactUs.HelpContactUsModel
import com.example.atlmovaapp.model.help_center.fag.HelpFagModel
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import com.google.android.material.tabs.TabLayout

class HelpCenterFragment : Fragment() {
    private lateinit var binding: FragmentHelpCenterBinding
    private val fagAdapter = HelpCenterAdapter()
    private val contactAdapter = HelpCenterContactAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpCenterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFag.adapter = fagAdapter
        binding.rvContact.adapter = contactAdapter
        setupTabLayout()


        val fagList = arrayListOf<HelpFagModel>()
        fagList.add(HelpFagModel("How to remove wishlist?"))
        fagList.add(HelpFagModel("How do I subscribe to premium?"))
        fagList.add(HelpFagModel("How do I can download movies?"))
        fagList.add(HelpFagModel("How to unsubscribe from premium?"))
        fagList.add(HelpFagModel("How to reset my password?"))
        fagList.add(HelpFagModel("How can I change my email address?"))
        fagList.add(HelpFagModel("How do I update my profile information?"))
        fagList.add(HelpFagModel("How to delete my account permanently?"))
        fagList.add(HelpFagModel("How do I manage my subscription?"))
        fagList.add(HelpFagModel("How to unsubscribe from premium?"))
        fagList.add(HelpFagModel("How do I remove a movie from my wishlist?"))
        fagList.add(HelpFagModel("How can I download movies for offline use?"))
        fagList.add(HelpFagModel("Why is my video not loading?"))
        fagList.add(HelpFagModel("How to fix playback issues?"))
        fagList.add(HelpFagModel("Why is my payment failing?"))
        fagList.add(HelpFagModel("Which payment methods are supported?"))
        fagList.add(HelpFagModel("How do I redeem a promo code?"))
        fagList.add(HelpFagModel("How can I contact customer support?"))
        fagList.add(HelpFagModel("How do I clear app cache?"))
        fagList.add(HelpFagModel("Why did I get logged out automatically?"))
        fagList.add(HelpFagModel("How do I enable notifications?"))
        fagList.add(HelpFagModel("How do I change the app language?"))
        fagList.add(HelpFagModel("Is my personal data secure?"))
        fagList.add(HelpFagModel("How do I report a bug or issue?"))

        fagAdapter.updateList(fagList)
        Log.d("fagList", fagList.toString())


        val contactList = arrayListOf<HelpContactUsModel>()
        contactList.add(HelpContactUsModel("Customer Service", R.drawable.customerserviceicon))
        contactList.add(HelpContactUsModel("WhatsApp", R.drawable.whatsappcontacusicon))
        contactList.add(HelpContactUsModel("Website", R.drawable.wepsitecontactusicon))
        contactList.add(HelpContactUsModel("Facebook", R.drawable.facebookcontactusicon))
        contactList.add(HelpContactUsModel("Instagram", R.drawable.instagramcontactusicon))
        contactList.add(HelpContactUsModel("Twitter", R.drawable.twittercontactusicon))

        contactAdapter.updateList(contactList)


        binding.tabLayout.getTabAt(0)?.select()
        binding.rvFag.visible()
        binding.fagCardView.visible()
        binding.rvContact.gone()


        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {
                    0 -> {
                        binding.rvFag.visible()
                        binding.fagCardView.visible()
                        binding.rvContact.gone()
                    }

                    1 -> {
                        binding.rvContact.visible()
                        binding.rvFag.gone()
                        binding.fagCardView.gone()
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}