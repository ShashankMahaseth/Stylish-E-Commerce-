package com.example.stylishe_commerceapp.data.remote

import Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)