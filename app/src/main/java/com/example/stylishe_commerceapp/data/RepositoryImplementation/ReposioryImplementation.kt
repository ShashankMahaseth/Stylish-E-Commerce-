package com.example.stylishe_commerceapp.data.RepositoryImplementation

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.domain.repository.PaymentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.razorpay.Checkout
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import org.json.JSONObject
import javax.inject.Inject

class PaymentRepositoryImplementation @Inject constructor(
    private val firestore: FirebaseFirestore,


) : PaymentRepository {
    override fun startPayment(amount: Long, activity: Activity): Flow<Result<String>> =
        callbackFlow {
            try {


                trySend(Result.Loading)
                val key =try {
                    firestore.collection("payment").document("razorpay123").get().await().getString("RAZORPAY_API")
                }catch (e: Exception){
                    null
                }


                Log.d("RazorPayKey", "startPayment: $key")
                val checkout = Checkout().apply {
                    setKeyID(key)
                    setImage(R.drawable.logoipsum_255_1)
                }
                val paymentOptions = JSONObject().apply {
                    put("name", "StylishEcommerce")
                    put("description", "Order Payment")
                    put("amount", amount)
                    put("currency", "INR")
                }
                checkout.open(activity, paymentOptions)
            } catch (e: Exception) {
                trySend(Result.Failure(e.message ?: "Something went wrong"))
            }
            awaitClose { }


        }
}