package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.data.RepositoryImplementation.ProductRepositoryImplementation
import com.example.stylishe_commerceapp.data.remote.ProductDto
import javax.inject.Inject

class GetProductUseCase @Inject constructor(val repository: ProductRepositoryImplementation) {
    suspend operator fun invoke(): Result<ProductDto> {
        return repository.getAllProducts()
    }
}