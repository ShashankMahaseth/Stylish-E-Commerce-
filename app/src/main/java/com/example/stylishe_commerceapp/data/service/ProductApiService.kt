package com.example.stylishe_commerceapp.data.service

import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.data.remote.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class ProductApiService @Inject constructor(val httpClient: HttpClient){

        suspend fun getProducts(limit: Int = 0): ProductDto {
            return httpClient.get("products") {
                parameter("limit", limit)
            }.body()
        }

}