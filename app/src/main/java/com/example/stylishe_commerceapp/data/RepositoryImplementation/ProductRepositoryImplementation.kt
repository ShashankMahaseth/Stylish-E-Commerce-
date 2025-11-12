package com.example.stylishe_commerceapp.data.RepositoryImplementation

import Product
import com.example.stylishe_commerceapp.data.remote.ProductDto
import com.example.stylishe_commerceapp.data.service.ProductApiService
import com.example.stylishe_commerceapp.domain.repository.ProductRepository
import com.example.stylishe_commerceapp.core.utils.Result
import javax.inject.Inject

class ProductRepositoryImplementation @Inject constructor(
    private val productApiService: ProductApiService
) : ProductRepository {

    override suspend fun getAllProducts(): Result<ProductDto> {
        return try {
            val response: ProductDto = productApiService.getProducts()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Failure(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun searchProduct(query: String): Result<ProductDto> {
        return try{
            val search: ProductDto = productApiService.searchProducts(query)
            Result.Success(search)
        }catch (e:Exception){
            Result.Failure(e.localizedMessage ?: "Unknown error occurred")
        }
    }
}
