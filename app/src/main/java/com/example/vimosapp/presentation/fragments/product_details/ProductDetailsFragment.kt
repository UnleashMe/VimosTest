package com.example.vimosapp.presentation.fragments.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.vimosapp.R
import com.example.vimosapp.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding: FragmentProductDetailsBinding
        get() = _binding!!

    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.product
        with(binding) {
            Glide.with(this@ProductDetailsFragment).load("https://vimos.ru${product.imgPreview}")
                .placeholder(R.drawable.placeholder).into(productImg)
            productName.text = product.name
            code.text = product.gcode.toString()
            price.text = getString(R.string.price, product.price)
            priceText.text = getString(R.string.price_text, product.units)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}