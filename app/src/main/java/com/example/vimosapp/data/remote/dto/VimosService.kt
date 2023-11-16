package com.example.vimosapp.data.remote.dto

import retrofit2.http.GET
import retrofit2.http.Query

interface VimosService {

    @GET("products")
    suspend fun getProducts(
        @Query("cat_id") catId: Int = 1000457,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("base_id") baseId: Int = 1,
        @Query("sort_type") sortType: Int = 0
    ): List<ProductResponseDtoItem>
}