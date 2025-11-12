package com.example.stylishe_commerceapp.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.data.remote.ProductDto
import com.example.stylishe_commerceapp.domain.usecase.GetProductUseCase
import com.example.stylishe_commerceapp.domain.usecase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase,val searchProductUseCase: SearchProductUseCase) :
    ViewModel() {
    private val _allProducts = MutableStateFlow<Result<ProductDto>>(Result.Idle)
    val allProducts = _allProducts.asStateFlow()


    private val _products = MutableStateFlow<Result<ProductDto>>(Result.Idle)
    val products = _products.asStateFlow()


    private val _categoryProducts = MutableStateFlow<Result<ProductDto>>(Result.Idle)
    val categoryProducts = _categoryProducts.asStateFlow()




    private val _searchProduct = MutableStateFlow<Result<ProductDto>>(Result.Idle)
    val searchProduct = _searchProduct.asStateFlow()





    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _products.value = Result.Loading
            try {
                val result = getProductUseCase()
                _products.value = result
            } catch (e: Exception) {
                _products.value = Result.Failure(e.localizedMessage ?: "Unknown error occurred")
            }

        }
    }
    fun reset() {
        getAllProducts()
    }

    fun getCompleteProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _allProducts.value = Result.Loading
            try {
                val result = getProductUseCase()
                _allProducts.value = result
            } catch (e: Exception) {
                _allProducts.value = Result.Failure(e.localizedMessage ?: "Unknown error occurred")
            }

        }
    }
    fun refresh() {
        getCompleteProducts()
    }
    fun getCategoryProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryProducts.value = Result.Loading
            try {
                val result = getProductUseCase()
                _categoryProducts.value = result
            } catch (e: Exception) {
                _categoryProducts.value = Result.Failure(e.localizedMessage ?: "Unknown error occurred")
            }

        }
    }
    fun categoryRefresh() {
        getCategoryProducts()
    }

    fun searchProduct(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            _searchProduct.value = Result.Loading
            try {
                val result = searchProductUseCase(query)
                _searchProduct.value = result
            }catch (e: Exception){
                _searchProduct.value = Result.Failure(e.localizedMessage ?: "Unknown error occurred")
            }

        }
    }


}



