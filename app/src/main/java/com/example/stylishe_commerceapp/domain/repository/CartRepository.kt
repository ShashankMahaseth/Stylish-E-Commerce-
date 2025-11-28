package com.example.stylishe_commerceapp.domain.repository

import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(product: Product, quantity: Int = 1)
    suspend fun removeFromCart(productId: Int)
    suspend fun updateQuantity(productId: Int, quantity: Int)
    suspend fun clearCart()
    suspend fun getCartItemCount(): Int
}



