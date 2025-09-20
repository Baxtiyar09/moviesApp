package com.example.atlmovaapp.screen.serach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.adapter.FilmsBigAdapter
import com.example.atlmovaapp.databinding.FragmentSearchBinding
import com.example.atlmovaapp.util.gone
import com.example.atlmovaapp.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var filmsBigAdapter: FilmsBigAdapter
    private val viewModel by viewModels<SerachViewModel>()
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsBigAdapter = FilmsBigAdapter { movieId ->
            val action = SearchFragmentDirections
                .actionSearchFragmentToDetailFragment(movieId)
            findNavController().navigate(action)
        }
        binding.rvSearch.adapter = filmsBigAdapter
        observeData()
        viewModel.getMovie()






        binding.serachInput.addTextChangedListener { editable ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                val query = editable?.toString()?.trim()
                if (!query.isNullOrEmpty()) {
                    viewModel.search(query)
                } else {
                    viewModel.getMovie()
                }
            }
        }

    }


    fun observeData() {
        viewModel.allMovies.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                binding.rvSearch.visible()
                binding.notfaundImage.gone()
                binding.notFaoundText.gone()
                binding.notfaounsorrytext.gone()
                filmsBigAdapter.updateList(list)

            } else {
                binding.rvSearch.gone()
                binding.notfaundImage.visible()
                binding.notFaoundText.visible()
                binding.notfaounsorrytext.visible()
            }
        }
        viewModel.serachMovies.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.rvSearch.visible()
                binding.notfaundImage.gone()
                binding.notFaoundText.gone()
                binding.notfaounsorrytext.gone()
                filmsBigAdapter.updateList(it)

            } else {
                binding.rvSearch.gone()
                binding.notfaundImage.visible()
                binding.notFaoundText.visible()
                binding.notfaounsorrytext.visible()
            }
        }


    }

}