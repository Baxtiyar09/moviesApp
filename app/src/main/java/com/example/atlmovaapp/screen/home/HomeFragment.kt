package com.example.atlmovaapp.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.atlmovaapp.adapter.FilmsAdapter
import com.example.atlmovaapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    private fun createAdapter() = FilmsAdapter { movieId ->
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId)
        findNavController().navigate(action)
    }

    private val popularAdapter by lazy { createAdapter() }
    private val nowPlayingAdapter by lazy { createAdapter() }
    private val top10Adapter by lazy { createAdapter() }
    private val upcomingAdapter by lazy { createAdapter() }


//    private val upacommingAdapter = FilmsAdapter { movieId ->
//        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId)
//        findNavController().navigate(action)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvPopular.adapter = popularAdapter
        binding.rvNewReleases.adapter = nowPlayingAdapter
        binding.rvTop10.adapter = top10Adapter
        binding.rvUpcoming.adapter = upcomingAdapter

        viewModel.getPopular()
        viewModel.getNowPlaying()
        viewModel.getTopRated()
        viewModel.getUpcoming()
        viewModel.loadFeaturedMovie()

        setOnClickListeners()
    }

    private fun observeData() {
        viewModel.popular.observe(viewLifecycleOwner) { list ->
            val sortedList = list.sortedByDescending { it.popularity ?: 0.0 }
            popularAdapter.updateList(sortedList)
        }

        viewModel.nowPlaying.observe(viewLifecycleOwner) { list ->
            val sortedList = list.sortedByDescending { it.releaseDate ?: "" }
            nowPlayingAdapter.updateList(sortedList)
        }

        viewModel.topRated.observe(viewLifecycleOwner) { list ->
            val sortedList = list.sortedByDescending { it.voteAverage ?: 0.0 }
            top10Adapter.updateList(sortedList)
        }

        viewModel.upcoming.observe(viewLifecycleOwner) { list ->
            val sortedList = list.sortedByDescending { it.releaseDate ?: "" }
            upcomingAdapter.updateList(sortedList)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.featuredMovie.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                binding.reklamFlimName.text = it.title
                binding.reklamFlimCategory.text = it.releaseDate
                Glide.with(this).load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                    .into(binding.reklamFlimImage)
            }
        }
    }


    private fun setOnClickListeners() {
        binding.seeAllNowPlaying.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("now_playing")
            findNavController().navigate(action)
        }

        binding.seeAllPopular.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("popular")
            findNavController().navigate(action)
        }
        binding.seeAllUpcoming.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("upcoming")
            findNavController().navigate(action)
        }
        binding.seeAllTop10.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("top_rated")
            findNavController().navigate(action)
        }

    }
}
