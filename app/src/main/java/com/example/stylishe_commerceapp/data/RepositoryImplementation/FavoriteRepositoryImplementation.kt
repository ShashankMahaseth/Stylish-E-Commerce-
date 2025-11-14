package com.example.stylishe_commerceapp.data.RepositoryImplementation

import com.example.stylishe_commerceapp.data.local.Dao.FavoriteDao
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImplementation(private val favoriteDao: FavoriteDao): FavoriteRepository{
    override fun getFavoriteProducts(): Flow<List<Product>> {
        return favoriteDao.getAllFavoriteProducts()
    }

    override suspend fun addFavoriteProduct(product: Product) {
        favoriteDao.insertFavoriteProduct(product)

    }

    override suspend fun removeFavoriteProduct(productId: Int) {
        favoriteDao.deleteFavoriteItem(productId)
    }

    override suspend fun isFavorite(productId: Int): Boolean {
        return favoriteDao.isFavorite(productId)
    }

    override suspend fun clearFavoriteProducts() {
        favoriteDao.clearFavoriteProducts()

    }



}