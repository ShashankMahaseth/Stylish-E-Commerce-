package com.example.stylishe_commerceapp.data.RepositoryImplementation

import com.example.stylishe_commerceapp.data.local.CartDataStore
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.domain.model.CartItem
import com.example.stylishe_commerceapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImplementation  @Inject constructor(private val cartDataStore: CartDataStore): CartRepository{
    override fun getCartItems(): Flow<List<CartItem>> {
       return cartDataStore.cartItems
    }
    override suspend fun addToCart(product: Product, quantity: Int) {
        cartDataStore.addToCart(product, quantity)
    }
    override suspend fun removeFromCart(productId: Int) {
        cartDataStore.removeFromCart(productId)
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int) {
        cartDataStore.updateQuantity(productId, quantity)
    }

    override suspend fun clearCart() {
       cartDataStore.clearCart()
    }
    override suspend fun getCartItemCount(): Int {
        return cartDataStore.getCartItemCount()
    }

}