package com.example.stylishe_commerceapp.presentation.PaymentPage

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.CheckOutPage.CheckOutPage
import com.example.stylishe_commerceapp.presentation.ViewModel.PaymentViewModel
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : ComponentActivity(), PaymentResultListener {

    private val viewModel: PaymentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Receive amount from UI (already converted to paisa)
        val amount = intent.getLongExtra("amount", 0L)
        viewModel.payNow(amount, this)
    }


    override fun onPaymentSuccess(paymentId: String) {
        viewModel.setPaymentResult(Result.Success(paymentId))


        val resultIntent = Intent().apply {
            putExtra("status", "success")
            putExtra("paymentId", paymentId)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onPaymentError(errorCode: Int, errorMessage: String?) {
        viewModel.setPaymentResult(Result.Failure(errorMessage ?: "Payment Failed"))

        val resultIntent = Intent().apply {
            putExtra("status", "failed")
        }
        setResult(RESULT_CANCELED, resultIntent)
        finish()
    }


}
