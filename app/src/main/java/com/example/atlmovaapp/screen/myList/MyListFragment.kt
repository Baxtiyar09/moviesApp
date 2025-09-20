package com.example.atlmovaapp.screen.myList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.adapter.FilmsBigAdapter
import com.example.atlmovaapp.databinding.FragmentMyListBinding
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListFragment : Fragment() {
    private lateinit var binding: FragmentMyListBinding
    private val viewModel by viewModels<MyListViewModel>()
    private lateinit var filmsBigAdapter: FilmsBigAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsBigAdapter = FilmsBigAdapter { movie ->
            findNavController().navigate(
                MyListFragmentDirections.actionMyListFragmentToDetailFragment(movie)
            )
        }

        binding.rvMyList.adapter = filmsBigAdapter

        observeData()
        viewModel.getAllMovie()



        binding.serachIcon.setOnClickListener {
            binding.searchInputLayout.visibility =
                if (binding.searchInputLayout.visibility == View.GONE) View.VISIBLE else View.GONE
        }


        binding.searchInput.addTextChangedListener { editable ->
            val query = editable?.toString()?.trim()
            if (!query.isNullOrEmpty()) {
                viewModel.searchMyList(query)
            } else {
                viewModel.getAllMovie()
            }
        }

    }

    private fun observeData() {
        viewModel.myList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                binding.rvMyList.gone()
                binding.mylistnullimage.visible()
                binding.YourListIsEmptytext.visible()
                binding.listEmptyText.visible()
            } else {
                binding.rvMyList.visible()
                binding.mylistnullimage.gone()
                binding.YourListIsEmptytext.gone()
                binding.listEmptyText.gone()

                val mappedList = list.map { myListModel ->
                    Result(
                        id = myListModel.id,
                        posterPath = myListModel.image,
                        voteAverage = myListModel.reyting,
                        overview = "",
                        releaseDate = "",
                        title = myListModel.title, // 🔹 artıq DB-dən gəlir
                        voteCount = 0,
                        popularity = 0.0,
                        adult = false,
                        backdropPath = "",
                        originalLanguage = "",
                        originalTitle = "",
                        video = false,
                        genreİds = emptyList()
                    )
                }
                filmsBigAdapter.updateList(mappedList)
            }
        }
    }

}
