package com.example.stylishe_commerceapp.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "favorite")
data class Product(
    val availabilityStatus: String? = null,
    val brand: String? = null,
    var category: String? = null,
    val description: String? = null,

    val discountPercentage: Double? = null,

    @PrimaryKey
    val id: Int ,
    val images: List<String>? = null,

    val minimumOrderQuantity: Int? = null,
    val price: Double? = null,
    val rating: Double? = null,
    val returnPolicy: String? = null,

    val shippingInformation: String? = null,
    val sku: String? = null,
    val stock: Int? = null,
    val tags: List<String>? = null,
    val thumbnail: String? = null,
    val title: String? = null,
    val warrantyInformation: String? = null,
    val weight: Int? = null
)
