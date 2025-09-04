package com.example.atlmovaapp.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovaapp.adapter.CreditsAdapter
import com.example.atlmovaapp.adapter.FilmsBigAdapter
import com.example.atlmovaapp.adapter.ReviewAdapter
import com.example.atlmovaapp.adapter.TrailersAdapter
import com.example.atlmovaapp.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel>()
    private val filmsAdapter = FilmsBigAdapter()
    private val creditsAdapter = CreditsAdapter()
    private val trailersAdapter = TrailersAdapter()
    private val reviewAdapter = ReviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.rvCredits.adapter = creditsAdapter
        binding.rvTrailers.adapter = trailersAdapter
        binding.rvMoreLikeThis.adapter = filmsAdapter

        binding.rvCredits.setHasFixedSize(true)
        binding.rvTrailers.setHasFixedSize(true)
        binding.rvMoreLikeThis.setHasFixedSize(true)
        binding.rvComment.setHasFixedSize(true)
        binding.rvTrailers.isNestedScrollingEnabled = false
        binding.rvMoreLikeThis.isNestedScrollingEnabled = false
        binding.rvComment.isNestedScrollingEnabled = false
        binding.rvCredits.isNestedScrollingEnabled = false


        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        setupTabLayout()

        binding.tabLayout.getTabAt(0)?.select()
        binding.rvTrailers.visibility = View.VISIBLE
        binding.rvMoreLikeThis.visibility = View.GONE
        binding.rvComment.visibility = View.GONE
    }

    private fun setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.rvTrailers.visibility = View.VISIBLE
                        binding.rvMoreLikeThis.visibility = View.GONE
                        binding.rvComment.visibility = View.GONE
                    }

                    1 -> {
                        binding.rvTrailers.visibility = View.GONE
                        binding.rvMoreLikeThis.visibility = View.VISIBLE
                        binding.rvComment.visibility = View.GONE
                    }

                    2 -> {
                        binding.rvTrailers.visibility = View.GONE
                        binding.rvMoreLikeThis.visibility = View.GONE
                        binding.rvComment.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun observeData() {
        viewModel.detail.observe(viewLifecycleOwner) {
            binding.detail = it
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.getMovieDetail(args.id)

        viewModel.credits.observe(viewLifecycleOwner) {
            creditsAdapter.updateCredits(it.cast)
        }
        viewModel.getMovieCredits(args.id)

        viewModel.trailers.observe(viewLifecycleOwner) {
            trailersAdapter.updateTrailers(it)
        }
        viewModel.getMovieTrailers(args.id)

        viewModel.moreLikeThis.observe(viewLifecycleOwner) {
            filmsAdapter.updateList(it)
        }
        viewModel.getMovieMoreLikeThis()

        viewModel.reviews.observe(viewLifecycleOwner) {
            Log.e("Reviews", "Size: ${it.size}, Data: $it")
            Toast.makeText(requireContext(), "Size: ${it}", Toast.LENGTH_LONG).show()
            reviewAdapter.updateList(it)
        }
        viewModel.getMovieReviews(args.id)
    }
}
