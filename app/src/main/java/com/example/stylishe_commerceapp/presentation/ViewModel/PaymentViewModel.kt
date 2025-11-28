package com.example.stylishe_commerceapp.presentation.ViewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    // State should be StateFlow not normal Flow
    private val _paymentState = MutableStateFlow<Result<String>>(Result.Loading)
    val paymentState = _paymentState.asStateFlow()

    //Remove wrong activity reference and use passed activity
    fun payNow(amount: Long, activity: Activity) {
        viewModelScope.launch {
            paymentRepository.startPayment(amount, activity)
                .collectLatest { result ->

                    _paymentState.value = result
                }
        }
    }

    // Directly update result after callback
    fun setPaymentResult(result: Result<String>) {
        _paymentState.value = result
    }
}
