package com.example.stylishe_commerceapp.domain.repository

import android.app.Activity
import com.example.stylishe_commerceapp.core.utils.Result
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun startPayment(amount: Long, activity: Activity): Flow<Result<String>>
}