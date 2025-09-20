package com.example.atlmovaapp.screen.detail

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovaapp.R
import com.example.atlmovaapp.adapter.CreditsAdapter
import com.example.atlmovaapp.adapter.FilmsBigAdapter
import com.example.atlmovaapp.adapter.ReviewAdapter
import com.example.atlmovaapp.adapter.TrailersAdapter
import com.example.atlmovaapp.databinding.FragmentDetailBinding
import com.example.atlmovaapp.model.database.DowloadModel
import com.example.atlmovaapp.model.database.MyListModel
import com.example.atlmovaapp.screen.download.DownloadViewModel
import com.example.atlmovaapp.screen.myList.MyListViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var filmsAdapter: FilmsBigAdapter
    private val creditsAdapter = CreditsAdapter()
    private val trailersAdapter = TrailersAdapter()
    private val reviewAdapter = ReviewAdapter()
    private val myListViewModel by viewModels<MyListViewModel>()
    private val downloadViewModel by viewModels<DownloadViewModel>()

    private var isSelected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        trailersAdapter.onItemClick = { video ->
            openYoutube(requireContext(), video.key)
        }


        filmsAdapter = FilmsBigAdapter { movieId ->
            val action = DetailFragmentDirections.actionDetailFragmentSelf(movieId)
            findNavController().navigate(action)
        }

        binding.rvCredits.adapter = creditsAdapter
        binding.rvTrailers.adapter = trailersAdapter
        binding.rvMoreLikeThis.adapter = filmsAdapter
        binding.rvComment.adapter = reviewAdapter

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

//        binding.kaydIcon.setOnClickListener {
//            binding.kaydIcon.setImageResource(R.drawable.mylisttrueicon)
//            val movie = viewModel.detail.value
//            movie?.let {
//                val myListModel = MyListModel(
//                    id = it.id,
//                    title = it.title,
//                    reyting = it.voteAverage,
//                    image = it.posterPath ?: ""
//                )
//                myListViewModel.addMovie(myListModel)
//                Toast.makeText(
//                    requireContext(), "Filme MyList-ə əlavə edildi ✅", Toast.LENGTH_SHORT
//                ).show()
//            }
//        }


        binding.downloadButton.setOnClickListener {
            showDownloadDialog()

        }

        binding.playButton.setOnClickListener {
            val trailers = viewModel.trailers.value
            if (!trailers.isNullOrEmpty()) {
                val officialTrailer = trailers.find { it.type == "Trailer" && it.official }
                val trailerToPlay = officialTrailer ?: trailers[0]
                openYoutube(requireContext(), trailerToPlay.key)
            } else {
                Toast.makeText(requireContext(), "Bu film üçün video yoxdur ❌", Toast.LENGTH_SHORT)
                    .show()
            }
        }







        setupTabLayout()

        binding.tabLayout.getTabAt(0)?.select()
        binding.rvTrailers.visibility = View.VISIBLE
        binding.rvMoreLikeThis.visibility = View.GONE
        binding.rvComment.visibility = View.GONE

        setupAddButton()
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
            reviewAdapter.updateList(it)
        }
        viewModel.getMovieReviews(args.id)
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

    private fun showDownloadDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_download, null)
        val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
        val progressText = dialogView.findViewById<TextView>(R.id.progressText)
        val hideButton = dialogView.findViewById<Button>(R.id.hideButton)


        val dialog =
            AlertDialog.Builder(requireContext()).setView(dialogView).setCancelable(false).create()

        hideButton.setOnClickListener { dialog.dismiss() }
        dialog.show()

        var progress = 0
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                progress += 1
                progressBar.progress = progress
                progressText.text = "$progress%"
                if (progress < 100) {
                    handler.postDelayed(this, 30) // hər 1 saniyədən artır
                } else {
                    progressText.text = "Download complete ✅"
                    addDownload()
                    lifecycleScope.launch {
                        delay(170)
                        dialog.dismiss()
                        Toast.makeText(
                            requireContext(), "Download complete ✅", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        handler.post(runnable)
    }

    fun addDownload() {
        val movie = viewModel.detail.value
        movie?.let {
            val downloadModel = DowloadModel(
                id = it.id,
                name = it.title,
                releaseDate = it.releaseDate.toString(),
                gb = it.voteAverage.toString(),
                image = it.backdropPath ?: ""
            )
            downloadViewModel.addDownload(downloadModel)
        }
    }


    private fun setupAddButton() {
        viewLifecycleOwner.lifecycleScope.launch {
            val added = viewModel.isMovieAdded(args.id)
            isSelected = added
            updateButtonUI()
        }

        binding.kaydIcon.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.detail.value?.let { movieDetail ->
                    val movieItem = MyListModel(
                        title = movieDetail.title ?: "No Title",
                        image = movieDetail.posterPath ?: "",
                        selected = !isSelected,
                        reyting = movieDetail.voteAverage ?: 0.0,
                        id = movieDetail.id ?: 0
                    )

                    if (isSelected) {
                        myListViewModel.deleteMovie(movieItem.id)
                        isSelected = false
                        Toast.makeText(requireContext(), "Movie Removed", Toast.LENGTH_SHORT).show()
                        updateButtonUI()
                    } else {
                        myListViewModel.addMovie(movieItem)
                        isSelected = true
                        Toast.makeText(requireContext(), "Movie Added", Toast.LENGTH_SHORT).show()
                        updateButtonUI()
                    }

                }

            }
        }

    }

    private fun updateButtonUI() {
        val image = if (isSelected) R.drawable.mylisttrueicon else R.drawable.movakaydicon
        binding.kaydIcon.setImageResource(image)
    }


}
