package com.example.atlmovaapp.screen.profile.join_Premium.reviewCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.FragmentReviewSummaryCardBinding
import kotlinx.coroutines.launch

class ReviewSummaryCardFragment : Fragment() {
    private lateinit var binding: FragmentReviewSummaryCardBinding
    private val args by navArgs<ReviewSummaryCardFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewSummaryCardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Kart adı və nömrəsi
        binding.cardNumber.text = args.cardNumber.ifEmpty { args.cardName }

// Kart şəkili
        binding.cardImage.setImageResource(args.cardImageRes)


        binding.confirmPaymentButton.setOnClickListener {
            if (binding.cardNumber.text.toString().isNotEmpty()) {
                binding.animationView.visibility = View.VISIBLE
                binding.animationView.repeatCount = 0
            } else {
                Toast.makeText(context, "Kart məlumatı yanlışdır ❌", Toast.LENGTH_SHORT).show()
            }
        }

        binding.animationView.addAnimatorUpdateListener {
            if (it.animatedFraction == 1f) {
                lifecycleScope.launch {
                    binding.animationView.visibility = View.GONE
                    findNavController().navigate(
                        R.id.action_reviewSummaryCardFragment_to_profileFragment
                    )
                }
            }
        }

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
