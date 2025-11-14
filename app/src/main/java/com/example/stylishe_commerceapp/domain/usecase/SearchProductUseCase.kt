package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.data.RepositoryImplementation.ProductRepositoryImplementation
import com.example.stylishe_commerceapp.data.remote.ProductDto
import javax.inject.Inject

class SearchProductUseCase @Inject constructor (val repository: ProductRepositoryImplementation){
    suspend operator fun invoke(query:String) : Result<ProductDto>{
        return repository.searchProduct(query)


    }

}