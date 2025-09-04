package com.example.atlmovaapp.screen.seeAll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovaapp.adapter.FilmsAdapter
import com.example.atlmovaapp.databinding.FragmentSeeAllBinding
import com.example.atlmovaapp.model.enum.MovieCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllFragment : Fragment() {

    private lateinit var binding: FragmentSeeAllBinding
    private val viewModel by viewModels<SeeAllViewModel>()
    private val args by navArgs<SeeAllFragmentArgs>()
    private lateinit var filmsAdapter: FilmsAdapter

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

        filmsAdapter = FilmsAdapter { movieId ->
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

        viewModel.fetchMovies(categoryEnum.apiValue)
    }

    private fun observeData() {
        viewModel.movies.observe(viewLifecycleOwner) { list ->
            filmsAdapter.updateList(list)
        }
    }
}
