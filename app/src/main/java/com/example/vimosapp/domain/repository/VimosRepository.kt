package com.example.vimosapp.domain.repository

import com.example.vimosapp.util.Resource
import com.example.vimosapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface VimosRepository {

    suspend fun getProducts(): Flow<Resource<List<Product>>>

}