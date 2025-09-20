package com.example.atlmovaapp.screen.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.R
import com.example.atlmovaapp.adapter.DownloadAdapter
import com.example.atlmovaapp.databinding.FragmentDownloadBinding
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadFragment : Fragment() {
    private lateinit var binding: FragmentDownloadBinding
    private val viewModel by viewModels<DownloadViewModel>()
    private lateinit var adapter: DownloadAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DownloadAdapter(
            onDeleteItemClick = { movieId ->
//                viewModel.deleteDownload(movieId)
//                viewModel.getAllDownload()
                alertDelete(movieId)

            },
            onItemClick = { movieId ->
                val action = DownloadFragmentDirections.actionDownloadFragmentToDetailFragment(movieId)
                findNavController().navigate(action)
            }
        )

        binding.rvDownload.adapter = adapter


        binding.rvDownload.adapter = adapter
        observeData()
        viewModel.getAllDownload()

        binding.searchIcon.setOnClickListener {
            binding.searchInputLayout.visibility =
                if (binding.searchInputLayout.visibility == View.GONE) View.VISIBLE else View.GONE
        }


        // ❌ ----> ❗{ Everything was difficult for me, but I managed.}  🤙🏻  ❌


        binding.searchInput.addTextChangedListener { editable ->
            val query = editable?.toString()?.trim()
            if (!query.isNullOrEmpty()) {
                viewModel.searchMyList(query)
            } else {
                viewModel.getAllDownload()
            }
        }






    }

    fun observeData() {
        viewModel.downloadFilm.observe(viewLifecycleOwner) { list ->

            if (list.isNullOrEmpty()) {
                binding.rvDownload.gone()
                binding.listEmpityImage.visible()
                binding.yourListIsEmptytext.visible()
                binding.listEmptyText2.visible()
            } else {
                binding.rvDownload.visible()
                binding.listEmpityImage.gone()
                binding.yourListIsEmptytext.gone()
                binding.listEmptyText2.gone()
            }
            val mappedList = list.map { downloadModel ->
                Result(
                    id = downloadModel.id,
                    posterPath = "",
                    voteAverage = downloadModel.gb.toDouble(),
                    overview = "",
                    releaseDate = downloadModel.releaseDate,
                    title = downloadModel.name,
                    voteCount = 0,
                    popularity = 0.0,
                    adult = false,
                    backdropPath = downloadModel.image,
                    originalLanguage = "",
                    originalTitle = "",
                    video = false,
                    genreİds = emptyList()
                )
            }
            adapter.updateList(mappedList)
        }
        viewModel.getAllDownload()

    }


    fun alertDelete(movieId: Int) {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.delete_alert_dialog, null)
        val alertDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogView)
            .create()

        val titleTextView = dialogView.findViewById<TextView>(R.id.tvTitle)
        val messageTextView = dialogView.findViewById<TextView>(R.id.tvMessage)
        val noButton = dialogView.findViewById<TextView>(R.id.btnNo)
        val yesButton = dialogView.findViewById<TextView>(R.id.btnYes)


        titleTextView.text = "Delete Movie"
        messageTextView.text = "Are you sure you want to delete this movie?"

        noButton.setOnClickListener {
            alertDialog.dismiss()
        }
        yesButton.setOnClickListener {
            viewModel.deleteDownload(movieId.toInt())
            alertDialog.dismiss()
        }
        alertDialog.show()

    }

}
