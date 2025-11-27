package com.example.stylishe_commerceapp.presentation.CheckOutPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.CartViewModel
import com.example.stylishe_commerceapp.presentation.ViewModel.SettingVIewModel
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@Composable
fun DeliveryScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
    settingVIewModel: SettingVIewModel =hiltViewModel()
) {
    val settingState by settingVIewModel.state.collectAsState()
    val state by cartViewModel.cartState.collectAsState()
    val deliveryCharge = 20.0
    val totalAmount = state.totalPrice.toDouble() + deliveryCharge
    CheckOutPage(
        alpha = 0.2f, text = "Total: ₹$totalAmount", buttonText = "Continue", onClicked = {
            navController.navigate(Routes.PaymentScreen)
        }, selectedIndex = 0, navController = navController, isBottomBar = true
    ) {
        Box(
            modifier = Modifier
                .shadow(6.dp)
                .background(color = colorResource(R.color.Snow))
                .fillMaxWidth()
                .height(400.dp),


            ) {
            Column {

                Text(
                    text = "Your Delivery Address",
                    fontSize = 32.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(4.dp)
                        .fillMaxWidth()
                        .height(300.dp), shape = RectangleShape, colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.WhiteSmoke)
                    )
                ) {
                    when {
                        settingState.isLoading -> {
                            LoadingIndicator()
                        }

                        settingState.error != null -> {
                            FailureComponent {
                                settingVIewModel.loadUserData()
                                settingVIewModel.loadUserProfile()
                            }
                        }

                        else -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.HomeWork,
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    tint = colorResource(R.color.Crimson)
                                )
                                Text(
                                    text = settingState.userProfile.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = 16.dp),
                                    color = colorResource(R.color.Crimson)
                                )
                                Text(
                                    text = settingState.userProfile.address,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(vertical = 16.dp)
                                )
                                Text(
                                    text = "${settingState.userProfile.city},${settingState.userProfile.pinCode}",
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "${settingState.userProfile.state},${settingState.userProfile.country}"
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                TextButton(onClick = {
                                    navController.navigate(Routes.SettingScreen)
                                }, modifier = Modifier.align(Alignment.End)) {
                                    Text(
                                        text = "Change Delivery Location",
                                        fontSize = 15.sp,
                                        color = colorResource(R.color.Crimson),
                                        textDecoration = TextDecoration.Underline

                                    )
                                }


                            }




                        }
                    }
                }

            }
        }
    }
}