package com.example.stylishe_commerceapp.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stylishe_commerceapp.data.remote.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao{
    @Query("SELECT * FROM favorite")
     fun getAllFavoriteProducts(): Flow<List<Product>>

    @Query("SELECT * FROM favorite WHERE id = :productId")
    suspend fun getFavoriteItem(productId: Int): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteProduct(product: Product)

@Query("DELETE FROM favorite WHERE id = :productId")
suspend fun deleteFavoriteItem(productId: Int)

@Query("SELECT EXISTS (SELECT 1 FROM favorite WHERE id = :productId)")
suspend fun isFavorite(productId: Int): Boolean

@Query("DELETE FROM favorite")
suspend fun clearFavoriteProducts()

@Query("SELECT COUNT(*) FROM favorite")
suspend fun getFavoriteCount(): Int
}

