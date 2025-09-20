package com.example.atlmovaapp.screen.profile.edit_Profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.FragmentEditProfileBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            binding.myProfileImage.setImageURI(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editIcon.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.UpdateButton.setOnClickListener {
            Toast.makeText(requireContext(), "Yenilik əlavə olundu ✅", Toast.LENGTH_SHORT).show()
            lifecycleScope.launch {
                delay(1000)
                findNavController().popBackStack()
            }
        }

        val countries = listOf(
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
            "Antigua and Barbuda", "Argentina", "Azerbaijan", "Australia",
            "Austria", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
            "Belarus", "Turkey"
        )
        val countryAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, countries)
        binding.autoCompleteCountry.setAdapter(countryAdapter)
        binding.autoCompleteCountry.threshold = 0
        binding.autoCompleteCountry.setDropDownBackgroundResource(R.color.gray)
        binding.autoCompleteCountry.setOnItemClickListener { parent, _, position, _ ->
            val selectedCountry = parent.getItemAtPosition(position).toString()
            Toast.makeText(
                requireContext(),
                "Selected Country: $selectedCountry",
                Toast.LENGTH_SHORT
            ).show()
        }

        val genderList = listOf("Male", "Female", "Other")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, genderList)
        binding.autoCompleteGender.setAdapter(genderAdapter)
        binding.autoCompleteGender.threshold = 0
        binding.autoCompleteGender.setDropDownBackgroundResource(R.color.gray)
        binding.autoCompleteGender.setOnItemClickListener { parent, _, position, _ ->
            val selectedGender = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Selected Gender: $selectedGender", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
