package com.example.vimosapp.presentation.fragments.starting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vimosapp.R
import com.example.vimosapp.databinding.FragmentStartingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartingFragment : Fragment() {

    private var _binding: FragmentStartingBinding? = null
    private val binding: FragmentStartingBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toProductsButton.setOnClickListener {
            findNavController().navigate(R.id.action_startingFragment_to_productListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}