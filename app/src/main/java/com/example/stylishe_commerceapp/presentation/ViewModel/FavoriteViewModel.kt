package com.example.stylishe_commerceapp.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteState(
    val allProducts: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    init {
        loadAllFavoriteProducts()
    }
    private fun loadAllFavoriteProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {


                favoriteRepository.getFavoriteProducts().collect { products ->
                    _state.value = _state.value.copy(
                        allProducts = products,
                        filteredProducts = products,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load Favorite Products"
                )

            }
        }
    }
        fun searchProducts(query: String) {
            _state.value = _state.value.copy(searchQuery = query)

            val filtered = if (query.isBlank()) {
                _state.value.allProducts
            } else {
                _state.value.allProducts.filter { product ->
                    product.title?.contains(query, ignoreCase = true) == true ||
                            product.description?.contains(query, ignoreCase = true) == true ||
                            product.brand?.contains(query, ignoreCase = true) == true ||
                            product.category?.contains(query, ignoreCase = true) == true
                }
            }

            _state.value = _state.value.copy(filteredProducts = filtered)
        }

        fun addFavoriteProduct(product: Product) {
            viewModelScope.launch {
                try {
                    favoriteRepository.addFavoriteProduct(product)
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        error = e.message ?: "Failed to add Favorite Product"
                    )
                }
            }
        }

        fun removeFavoriteProduct(productId: Int) {
            viewModelScope.launch {
                try {
                    favoriteRepository.removeFavoriteProduct(productId)
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        error = e.message ?: "Failed to remove Favorite Product"
                    )
                }
            }

        }
    suspend fun isFavorite(productId: Int): Boolean {
        return try {
            return favoriteRepository.isFavorite(productId)
        }catch (e: Exception){
            false
        }

    }
}