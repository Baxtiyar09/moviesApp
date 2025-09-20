package com.example.atlmovaapp.screen.home

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.atlmovaapp.adapter.FilmsAdapter
import com.example.atlmovaapp.databinding.FragmentHomeBinding
import com.example.atlmovaapp.model.database.MyListModel
import com.example.atlmovaapp.screen.detail.DetailViewModel
import com.example.atlmovaapp.screen.myList.MyListViewModel
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val trailersViewModel by viewModels<DetailViewModel>()
    private val myListViewModel by viewModels<MyListViewModel>()


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

        binding.searchIcon.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        binding.notficationIcon.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNotficationFragment2()
            findNavController().navigate(action)
        }

        binding.buttonPlay.setOnClickListener {
            val trailers = trailersViewModel.trailers.value
            if (!trailers.isNullOrEmpty()) {
                val officialTrailer = trailers.find { it.type == "Trailer" && it.official }
                val trailerToPlay = officialTrailer ?: trailers[1]
                openYoutube(requireContext(), trailerToPlay.key)
            } else {
                Toast.makeText(requireContext(), "Bu film üçün video yoxdur ❌", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.reklamMylistElaveButton.setOnClickListener {
            val movie = viewModel.featuredMovie.value
            movie?.let {
                val myListModel = MyListModel(
                    id = movie.id ?: 0,
                    title = movie.title,
                    reyting = movie.voteAverage ?: 0.0,
                    image = movie.posterPath ?: "",
                    selected = false
                )
                myListViewModel.addMovie(myListModel)
                Toast.makeText(
                    requireContext(), "${movie.title} My List-ə əlavə olundu ✅", Toast.LENGTH_SHORT
                ).show()

            }
        }
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

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.rvPopular.gone()
                binding.rvNewReleases.gone()
                binding.rvTop10.gone()
                binding.rvUpcoming.gone()
                binding.reklamFlimCategory.gone()
                binding.reklamFlimImage.gone()
                binding.reklamFlimName.gone()
                binding.searchIcon.gone()
                binding.seeAllPopular.gone()
                binding.seeAllNowPlaying.gone()
                binding.seeAllTop10.gone()
                binding.seeAllUpcoming.gone()
                binding.notficationIcon.gone()
                binding.top10Films.gone()
                binding.upcomingFilms.gone()
                binding.popularFilms.gone()
                binding.newReleasesFlims.gone()
                binding.animationView2.visible()
                binding.animationView2.playAnimation()
            } else {
                lifecycleScope.launch {
                    delay(200)

                    binding.animationView2.gone()
                    binding.animationView2.pauseAnimation()
                    binding.rvPopular.visible()
                    binding.rvNewReleases.visible()
                    binding.rvTop10.visible()
                    binding.rvUpcoming.visible()
                    binding.reklamFlimCategory.visible()
                    binding.reklamFlimImage.visible()
                    binding.reklamFlimName.visible()
                    binding.searchIcon.visible()
                    binding.seeAllPopular.visible()
                    binding.seeAllNowPlaying.visible()
                    binding.seeAllTop10.visible()
                    binding.seeAllUpcoming.visible()
                    binding.notficationIcon.visible()
                    binding.top10Films.visible()
                    binding.upcomingFilms.visible()
                    binding.popularFilms.visible()
                    binding.newReleasesFlims.visible()
                }
            }
        }

        viewModel.featuredMovie.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                binding.reklamFlimName.text = it.title
                binding.reklamFlimCategory.text = it.releaseDate
                Glide.with(this).load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                    .into(binding.reklamFlimImage)
            }
        }
    }


    private fun openYoutube(context: Context, youtubeKey: String?) {
        val youtubeAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$youtubeKey"))
        val youtubeWebIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$youtubeKey"))

        try {
            context.startActivity(youtubeAppIntent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(youtubeWebIntent)
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
