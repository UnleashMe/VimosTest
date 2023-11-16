package com.example.vimosapp.presentation.fragments.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vimosapp.util.Resource
import com.example.vimosapp.domain.model.Product
import com.example.vimosapp.domain.repository.VimosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository: VimosRepository) :
    ViewModel() {

    private val _productList: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productList = _productList.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getProducts().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.update { true }
                    }

                    is Resource.Error -> {
                        _isLoading.update { false }
                        _error.update { resource.message }
                    }

                    is Resource.Success -> {
                        _isLoading.update { false }
                        _productList.update { resource.data ?: listOf() }
                    }
                }
            }
        }
    }
}