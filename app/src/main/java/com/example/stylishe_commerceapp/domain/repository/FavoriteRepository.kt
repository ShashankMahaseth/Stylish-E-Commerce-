package com.example.stylishe_commerceapp.domain.repository

import com.example.stylishe_commerceapp.data.remote.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteProducts(): Flow<List<Product>>

    suspend fun addFavoriteProduct(product: Product)

    suspend fun removeFavoriteProduct(productId: Int)

    suspend fun isFavorite(productId: Int): Boolean

    suspend fun clearFavoriteProducts()



}