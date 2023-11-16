package com.example.vimosapp.presentation.fragments.product_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vimosapp.R
import com.example.vimosapp.databinding.ProductItemBinding
import com.example.vimosapp.domain.model.Product

class ProductsAdapter(val onClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var products: List<Product> = listOf()

    fun setProducts(products: List<Product>) {
        val diffUtilCallback = ProductDiffUtilCallback(this.products, products)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        this.products = products
        diffResult.dispatchUpdatesTo(this)
    }

    class ProductViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        val context = holder.itemView.context

        with(holder.binding) {
            productName.text = product.name
            Glide.with(context).load("https://vimos.ru${product.imgPreview}")
                .placeholder(R.drawable.placeholder).into(productImg)
            code.text = product.gcode.toString()
            price.text = context.getString(R.string.price, product.price)
            holder.itemView.setOnClickListener {
                onClick(product)
            }
            priceText.text = context.getString(R.string.price_text, product.units)
        }
    }
}