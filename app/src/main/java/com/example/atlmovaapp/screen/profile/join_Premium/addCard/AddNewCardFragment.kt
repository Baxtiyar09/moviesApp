package com.example.atlmovaapp.screen.profile.join_Premium.addCard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.databinding.FragmentAddNewCardBinding
import com.example.atlmovaapp.model.database.CardModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewCardFragment : Fragment() {
    private lateinit var binding: FragmentAddNewCardBinding
    private val viewModel by viewModels<AddNewCardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔙 Geri qayıtmaq
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        // 🔹 Kart nömrəsi formatı (4 rəqəmdən bir boşluq)
        binding.kartNomresi.addTextChangedListener(object : TextWatcher {
            private var isFormatting: Boolean = false
            private var cursorPosition: Int = 0

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                cursorPosition = binding.kartNomresi.selectionStart
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return
                isFormatting = true

                val digits = s.toString().replace(" ", "")
                val formatted = StringBuilder()

                for (i in digits.indices) {
                    formatted.append(digits[i])
                    if ((i + 1) % 4 == 0 && i + 1 != digits.length) {
                        formatted.append(" ")
                    }
                }

                binding.kartNomresi.setText(formatted.toString())
                binding.kartNomresi.setSelection(minOf(cursorPosition + 1, formatted.length))

                isFormatting = false
            }
        })

        binding.kartTarixi.addTextChangedListener(object : TextWatcher {
            private var isFormatting: Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return
                isFormatting = true

                val digits = s.toString().replace("/", "")
                val formatted = StringBuilder()

                for (i in digits.indices) {
                    formatted.append(digits[i])
                    if ((i + 1) % 2 == 0 && i + 1 != digits.length) {
                        formatted.append("/")
                    }
                }

                binding.kartTarixi.setText(formatted.toString())
                binding.kartTarixi.setSelection(formatted.length)

                isFormatting = false
            }
        })

        // 🔘 Kart əlavə etmək
        binding.AddButton.setOnClickListener {
            if (!binding.kartAdi.text.isNullOrEmpty() &&
                !binding.kartNomresi.text.isNullOrEmpty() &&
                !binding.kartTarixi.text.isNullOrEmpty() &&
                !binding.kartCvv.text.isNullOrEmpty()
            ) {
                val cardName = binding.kartAdi.text.toString()
                val cardNumber = binding.kartNomresi.text.toString()
                val expiryDate = binding.kartTarixi.text.toString()
                val cvv = binding.kartCvv.text.toString()

                val cardModel = CardModel(
                    id = 0,
                    name = cardName,
                    number = cardNumber,
                    date = expiryDate,
                    cvv = cvv,
                    isDefault = false,
                    selected = false
                )

                viewModel.addCard(cardModel)

                Toast.makeText(requireContext(), "Kart uğurla əlavə olundu ✅", Toast.LENGTH_SHORT).show()

                findNavController().navigate(
                    AddNewCardFragmentDirections.actionAddNewCardFragmentToSelectCardFragment()
                )
            } else {
                Toast.makeText(requireContext(), "Boş xanalari doldurun ❌", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
