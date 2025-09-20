package com.example.atlmovaapp.screen.notification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.adapter.DownloadAdapter
import com.example.atlmovaapp.adapter.NotificationAdapter
import com.example.atlmovaapp.databinding.FragmentNotfication2Binding
import com.example.atlmovaapp.databinding.FragmentNotficationBinding
import com.example.atlmovaapp.screen.download.DownloadFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotficationFragment : Fragment() {
  private lateinit var binding: FragmentNotfication2Binding
  private lateinit var adapter: NotificationAdapter
  private val viewModel by viewModels<NotificationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotfication2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        viewModel.getPopular()

        adapter = NotificationAdapter(
            onItemClick = { movieId ->
                val action = NotficationFragmentDirections.actionNotficationFragment2ToDetailFragment(movieId)
                findNavController().navigate(action)
            }
        )

        binding.rvNotfication.adapter = adapter

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }


    }

    fun observeData(){
        viewModel.popular.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner){
            Log.e("TAG", "observeData: $it")
        }

    }
}