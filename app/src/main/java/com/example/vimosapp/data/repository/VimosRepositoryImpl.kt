package com.example.vimosapp.data.repository

import com.example.vimosapp.util.Resource
import com.example.vimosapp.data.remote.dto.VimosService
import com.example.vimosapp.domain.model.Product
import com.example.vimosapp.domain.repository.VimosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class VimosRepositoryImpl @Inject constructor(private val vimosService: VimosService) :
    VimosRepository {

    override suspend fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            val response = vimosService.getProducts()
            if (response.isEmpty()) {
                emit(Resource.Error("Не удалось загрузить данные"))
            }
           emit(Resource.Success(response.map { it.toProduct() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Проблема с подключением к интернету"))
        }
    }.flowOn(Dispatchers.IO)
}