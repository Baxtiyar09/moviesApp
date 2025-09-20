package com.example.atlmovaapp.screen.seeAll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovaapp.adapter.FilmsAdapter
import com.example.atlmovaapp.adapter.FilmsBigAdapter
import com.example.atlmovaapp.databinding.FragmentSeeAllBinding
import com.example.atlmovaapp.model.enum.MovieCategory
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllFragment : Fragment() {

    private lateinit var binding: FragmentSeeAllBinding
    private val viewModel by viewModels<SeeAllViewModel>()
    private val args by navArgs<SeeAllFragmentArgs>()
    private lateinit var filmsAdapter: FilmsBigAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsAdapter = FilmsBigAdapter { movieId ->
            val action = SeeAllFragmentDirections
                .actionSeeAllFragmentToDetailFragment(movieId)
            findNavController().navigate(action)
        }

        binding.rvList.adapter = filmsAdapter
        observeData()


        val categoryEnum = MovieCategory.fromApiValue(args.category)
        binding.getCategoryTitle.text = categoryEnum.displayName

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.serachIcon.setOnClickListener {
            binding.searchInputLayout.visibility =
                if (binding.searchInputLayout.visibility == View.GONE) View.VISIBLE else View.GONE
        }



        binding.searchInput.addTextChangedListener { editable ->
            val query = editable?.toString()?.trim().orEmpty()
            viewModel.filterMovies(query) // 🔎 yalnız mövcud listdən filter
        }


        viewModel.fetchMovies(categoryEnum.apiValue)
    }

    private fun observeData() {
        viewModel.movies.observe(viewLifecycleOwner) { list ->

            if (list.isNullOrEmpty()) {
                binding.mylistnullimage.visible()
                binding.yourListIsEmptytext.visible()
                binding.listEmptyText.visible()
                binding.rvList.gone()
                return@observe
            }else{
                binding.mylistnullimage.gone()
                binding.yourListIsEmptytext.gone()
                binding.listEmptyText.gone()
                binding.rvList.visible()
            }

            filmsAdapter.updateList(list)
        }
    }
}
