package com.example.vimosapp.data.remote.dto

import com.example.vimosapp.domain.model.Product
import com.squareup.moshi.Json

data class ProductResponseDtoItem(
    @Json(name = "cat_id")
    val catId: Int = 0,
    val gcode: Int = 0,
    @Json(name = "has_discount")
    val hasDiscount: Int = 0,
    @Json(name = "img_preview")
    val imgPreview: String = "",
    @Json(name = "is_stocked")
    val isStocked: Int = 0,
    @Json(name = "leftover_controlled")
    val leftoverControlled: Boolean = false,
    val name: String = "",
    val new: Int = 0,
    @Json(name = "old_price")
    val oldPrice: Int = 0,
    @Json(name = "outstock_reason")
    val outstockReason: Any? = null,
    val pack: Int = 0,
    val price: Int = 0,
    val prior: Int = 0,
    val qty: Int = 0,
    val sale: Boolean = false,
    val stock: Int = 0,
    val units: String = ""
) {
    fun toProduct(): Product {
        return Product(
            gcode = this.gcode,
            imgPreview = this.imgPreview,
            name = this.name,
            price = this.price,
            units = this.units
        )
    }
}