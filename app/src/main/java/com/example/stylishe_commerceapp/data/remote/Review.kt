package com.example.stylishe_commerceapp.data.remote

import kotlinx.serialization.Serializable


@Serializable
data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
)