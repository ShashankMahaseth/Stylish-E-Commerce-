package com.example.stylishe_commerceapp.domain.repository

import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.data.remote.ProductDto

interface ProductRepository {
    suspend fun getAllProducts(): Result<ProductDto>
    suspend fun searchProduct(query: String) : Result<ProductDto>
}