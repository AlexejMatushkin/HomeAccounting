package com.practicum.homeaccounting.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.homeaccounting.domain.Product
import com.practicum.homeaccounting.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<ProductListState>(ProductListState.Loading)
    val products: StateFlow<ProductListState> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            getAllProductsUseCase()
                .catch { exception ->
                    _products.value = ProductListState.Error(exception.message ?: "Unknown error")
                }
                .collect { products ->
                    _products.value = ProductListState.Success(products)
                }
        }
    }

    sealed class ProductListState {
        object Loading : ProductListState()
        data class Success(val products: List<Product>) : ProductListState()
        data class Error(val message: String) : ProductListState()
    }
}
