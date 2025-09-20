package com.example.atlmovaapp.screen.profile.language

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.adapter.LanguageAdapter
import com.example.atlmovaapp.databinding.FragmentLanguageBinding
import com.example.atlmovaapp.model.language.LanguageModel

class LanguageFragment : Fragment() {
    private lateinit var binding: FragmentLanguageBinding
    private lateinit var adapter: LanguageAdapter
    private var selectedLanguage: String = "English (US)"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLanguageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LanguageAdapter { selected ->
            clearSuggestedSelection()

            selectedLanguage = selected.language
            Log.d("language", "Selected from list: $selectedLanguage")
        }
        binding.rvLanguage.adapter = adapter


        setSuggestedSelection("English (US")

        binding.EnglishUSText.setOnClickListener {
            adapter.clearSelection()
            setSuggestedSelection("English (US)")
        }

        binding.EnglishUKText.setOnClickListener {
            adapter.clearSelection()
            setSuggestedSelection("English (UK)")
        }



        val languageList = arrayListOf<LanguageModel>()
        languageList.add(LanguageModel("Azerbaijani"))
        languageList.add(LanguageModel("English"))
        languageList.add(LanguageModel("Russian"))
        languageList.add(LanguageModel("Turkish"))
        languageList.add(LanguageModel("Hindi"))
        languageList.add(LanguageModel("Bengali"))
        languageList.add(LanguageModel("Urdu"))
        languageList.add(LanguageModel("Persian"))
        languageList.add(LanguageModel("Greek"))
        languageList.add(LanguageModel("Polish"))
        languageList.add(LanguageModel("Romanian"))
        languageList.add(LanguageModel("Hungarian"))
        languageList.add(LanguageModel("Czech"))
        languageList.add(LanguageModel("Swedish"))
        languageList.add(LanguageModel("Norwegian"))
        languageList.add(LanguageModel("Finnish"))
        languageList.add(LanguageModel("Danish"))
        languageList.add(LanguageModel("Hebrew"))
        languageList.add(LanguageModel("Thai"))
        languageList.add(LanguageModel("Vietnamese"))
        languageList.add(LanguageModel("Malay"))
        languageList.add(LanguageModel("Indonesian"))
        languageList.add(LanguageModel("Filipino"))
        languageList.add(LanguageModel("Serbian"))
        languageList.add(LanguageModel("Croatian"))
        languageList.add(LanguageModel("Slovak"))
        languageList.add(LanguageModel("Bulgarian"))
        languageList.add(LanguageModel("Georgian"))
        languageList.add(LanguageModel("German"))
        languageList.add(LanguageModel("French"))
        languageList.add(LanguageModel("Spanish"))
        languageList.add(LanguageModel("Chinese"))
        languageList.add(LanguageModel("Arabic"))
        languageList.add(LanguageModel("Ukrainian"))
        languageList.add(LanguageModel("Italian"))
        languageList.add(LanguageModel("Japanese"))
        languageList.add(LanguageModel("Korean"))
        languageList.add(LanguageModel("Portuguese"))
        languageList.add(LanguageModel("Dutch"))
        languageList.add(LanguageModel("Armenian"))

        adapter.updateList(languageList)

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

    }


    private fun setSuggestedSelection(language: String) {
        selectedLanguage = language

        if (language == "English (US)") {
            binding.imageViewRadioTrue.setImageResource(R.drawable.radiotrue)
            binding.imageViewRadioFalse.setImageResource(R.drawable.radiofalse)
        } else if (language == "English (UK)") {
            binding.imageViewRadioTrue.setImageResource(R.drawable.radiofalse)
            binding.imageViewRadioFalse.setImageResource(R.drawable.radiotrue)
        }
    }

    private fun clearSuggestedSelection() {
        binding.imageViewRadioTrue.setImageResource(R.drawable.radiofalse)
        binding.imageViewRadioFalse.setImageResource(R.drawable.radiofalse)
    }
}