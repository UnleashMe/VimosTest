package com.example.vimosapp.presentation.fragments.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vimosapp.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding: FragmentProductListBinding
        get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductsAdapter {
            val action =
                ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(
                    it,
                    it.name
                )
            findNavController().navigate(action)
        }
        val manager = LinearLayoutManager(requireContext())
        binding.productsRV.layoutManager = manager
        binding.productsRV.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect {
                if (it) {
                    binding.productsRV.visibility = View.GONE
                    binding.loadingIndicator.visibility = View.VISIBLE
                } else {
                    binding.productsRV.visibility = View.VISIBLE
                    binding.loadingIndicator.visibility = View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productList.collect {
                if (it.isNotEmpty()) {
                    adapter.setProducts(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect {
                if (it != null) {
                    binding.productsRV.visibility = View.GONE
                    binding.errorText.text = it
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.productsRV.adapter= null
        _binding = null
    }
}