package com.example.stylishe_commerceapp.domain.model

import com.example.stylishe_commerceapp.data.remote.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
data class CartItem (
    val product : Product,
    val quantity : Int=1,

)


