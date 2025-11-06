package com.example.stylishe_commerceapp.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.data.remote.ProductDto
import com.example.stylishe_commerceapp.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase): ViewModel(){
    private val _products = MutableStateFlow<Result<ProductDto>>(Result.Idle)
    val products= _products.asStateFlow()
init {
    getAllProducts()
}
    fun getAllProducts(){
        viewModelScope.launch {
            try {
                _products.value = Result.Loading
                val result = getProductUseCase()
                _products.value = result
            }catch (e: Exception){
                _products.value = Result.Failure(e.localizedMessage ?: "Unknown error occurred")
            }

            }
        }

    }



