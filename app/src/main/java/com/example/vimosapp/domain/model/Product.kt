package com.example.vimosapp.domain.model

import java.io.Serializable

data class Product(
    val gcode: Int = 0,
    val imgPreview: String = "",
    val name: String = "",
    val price: Int = 0,
    val units: String = ""
): Serializable
