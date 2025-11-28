package com.example.stylishe_commerceapp.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.domain.model.CartItem
import com.example.stylishe_commerceapp.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round
import kotlin.math.roundToInt


data class CartState(
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalPrice: String = "0.0",
    val totalItems: Int = 0
)


@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {
    private val _cartState = MutableStateFlow(CartState())
    val cartState = _cartState.asStateFlow()

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            _cartState.value = _cartState.value.copy(isLoading = true)
            try {
                cartRepository.getCartItems().collect { items ->
                    val total = items.sumOf {

                        val price = (it.product.price ?: 0.0) * 70.0
                        val originalPrice = round(price * 10) / 10
                        val discountedPrice =
                            originalPrice * (1 - (it.product.discountPercentage?.div(100.0) ?: 0.0))
                        val originalDiscountedPrice = round(discountedPrice * 10) / 10
                        ((originalDiscountedPrice * it.quantity * 10).roundToInt() / 10.0)

                    }
                    val formattedTotal = String.format("%.1f", total)


                    val totalItems = items.sumOf { it.quantity }
                    _cartState.value = _cartState.value.copy(
                        cartItems = items,
                        isLoading = false,
                        totalPrice = formattedTotal,

                        totalItems = totalItems,
                        error = null
                    )

                }

            } catch (e: Exception) {
                _cartState.value = _cartState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load cart"
                )

            }

        }
    }

    fun addToCart(product: Product, quantity: Int = 1) {
        viewModelScope.launch {
            try {
                cartRepository.addToCart(product, quantity)
            } catch (e: Exception) {
                _cartState.value = _cartState.value.copy(
                    error = e.message ?: "Failed to Load Add to Cart"
                )
            }

        }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            try {
                cartRepository.removeFromCart(productId)
            } catch (e: Exception) {
                _cartState.value = _cartState.value.copy(

                    error = e.message ?: "Failed to Load Remove from Cart"
                )

            }
        }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            try {
                cartRepository.updateQuantity(productId, quantity)
            } catch (e: Exception) {
                _cartState.value = _cartState.value.copy(
                    error = e.message ?: "Failed to Load Update Quantity"
                )
            }
        }

    }

    fun clearCart() {
        viewModelScope.launch {
            try {
                cartRepository.clearCart()
            } catch (e: Exception) {
                _cartState.value = _cartState.value.copy(
                    error = e.message ?: "Failed to Load Clear Cart"
                )
            }
        }
    }
}

