package com.example.stylishe_commerceapp.presentation.CheckOutPage

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.PaymentPage.PaymentActivity
import com.example.stylishe_commerceapp.presentation.ViewModel.CartViewModel

@Composable
fun PaymentScreen(cartViewModel: CartViewModel, navController: NavController) {
    val state by cartViewModel.cartState.collectAsState()
    val deliveryCharge = 20.0
    val totalAmount = state.totalPrice.toDouble() + deliveryCharge
    val context = LocalContext.current
    val paymentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val status = result.data?.getStringExtra("status")
        if (status == "success") {
            cartViewModel.clearCart()
        }
        navController.navigate(Routes.ConformationScreen(status = status))

    }


    CheckOutPage(
        alpha = 0.2f,
        text = "Total: ₹$totalAmount", buttonText = "Pay Now",
        onClicked = {

            val amount = (totalAmount * 100).toLong()
            val intent = Intent(context, PaymentActivity::class.java).apply {
                putExtra("amount", amount)

            }
            paymentLauncher.launch(intent)

        }, selectedIndex = 1,
        navController
    ) {
        Box(
            modifier = Modifier
                .shadow(4.dp)
                .background(color = colorResource(R.color.Snow))
                .fillMaxWidth()
                .height(400.dp),

            ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(R.drawable.razor),
                    contentDescription = null,
                    modifier = Modifier
                        .scale(1.5f)
                        .align(Alignment.CenterHorizontally)

                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Cart Value",
                        color = colorResource(R.color.silver),
                        fontSize = 18.sp
                    )
                    Text(
                        text = "₹${state.totalPrice}",
                        color = colorResource(R.color.silver),
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Delivery Charge",
                        color = colorResource(R.color.silver),
                        fontSize = 18.sp
                    )
                    Text(
                        text = "₹$deliveryCharge",
                        color = colorResource(R.color.silver),
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Sub Total",
                        color = colorResource(R.color.Black),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "₹${totalAmount}",
                        color = colorResource(R.color.Black),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(modifier = Modifier.padding(vertical = 16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Amount Payable",
                        color = colorResource(R.color.Black),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "₹${totalAmount}",
                        color = colorResource(R.color.Black),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


            }

        }

    }
}