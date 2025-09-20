package com.example.atlmovaapp.screen.profile.join_Premium.selectCard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.adapter.CardAdapter
import com.example.atlmovaapp.databinding.FragmentSelectCardBinding
import com.example.atlmovaapp.model.database.CardModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectCardFragment : Fragment() {

    private lateinit var binding: FragmentSelectCardBinding
    private lateinit var cardAdapter: CardAdapter
    private val viewModel by viewModels<SelectCardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardAdapter = CardAdapter { selectedCard ->
            // Seçilmiş kart burada istifadə oluna bilər
            Log.d("SelectedCard", "Selected: ${selectedCard.name}")
        }

//        viewModel.deleteCard()
        binding.rvPaymentCard.adapter = cardAdapter
        observeData()

        binding.backicon.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.addNewCardButton.setOnClickListener {
            findNavController().navigate(R.id.action_selectCardFragment_to_addNewCardFragment)
        }

        binding.continueButton.setOnClickListener {
            val selectedCard = cardAdapter.cards.find { it.selected }
            if (selectedCard != null) {
                // Image res
                val imageRes = when (selectedCard.name) {
                    "PayPal" -> R.drawable.paypalicon
                    "Google Pay" -> R.drawable.googleicon
                    "Apple Pay" -> R.drawable.appleicon
                    else -> R.drawable.movamastercard
                }

                findNavController().navigate(
                    SelectCardFragmentDirections.actionSelectCardFragmentToReviewSummaryCardFragment(
                        cardNumber = selectedCard.number,
                        cardName = selectedCard.name,
                        cardImageRes = imageRes
                    )
                )
            } else {
                Toast.makeText(context, "Lütfen Bir Kart Seçiniz", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun observeData() {
        viewModel.cards.observe(viewLifecycleOwner) { dbCards ->

            val defaultCards = listOf(
                CardModel(
                    id = -1,
                    name = "PayPal",
                    number = "",
                    date = "",
                    cvv = "",
                    isDefault = true,
                    selected = true
                ),
                CardModel(
                    id = -2,
                    name = "Google Pay",
                    number = "",
                    date = "",
                    cvv = "",
                    isDefault = true,
                    selected = false
                ),
                CardModel(
                    id = -3,
                    name = "Apple Pay",
                    number = "",
                    date = "",
                    cvv = "",
                    isDefault = true,
                    selected = false
                )
            )

            val allCards = defaultCards + dbCards
            cardAdapter.updateList(allCards)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getAllCards()
    }
}
