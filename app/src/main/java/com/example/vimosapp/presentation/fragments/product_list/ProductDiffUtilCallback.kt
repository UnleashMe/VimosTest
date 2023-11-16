package com.example.vimosapp.presentation.fragments.product_list

import androidx.recyclerview.widget.DiffUtil
import com.example.vimosapp.domain.model.Product

class ProductDiffUtilCallback(
    private val oldList: List<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].gcode == newList[newItemPosition].gcode
    }
}