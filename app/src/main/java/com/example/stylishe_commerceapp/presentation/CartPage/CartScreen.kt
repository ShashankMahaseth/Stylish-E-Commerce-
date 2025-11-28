package com.example.stylishe_commerceapp.presentation.CartPage

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirportShuttle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Components.CartComponents.CartCard
import com.example.stylishe_commerceapp.presentation.Components.CartComponents.CartTopAppBar
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.CartViewModel
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@Composable


fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val context = LocalContext.current
    val cartState by cartViewModel.cartState.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.WhiteSmoke),
        topBar = {
            CartTopAppBar {
                navController.navigate(Routes.Home)

            }
        }
    ) { innerPadding ->
        BackHandler{
            navController.navigate(Routes.Home)

        }
        if (cartState.cartItems.isEmpty()) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.addcart),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )

                Button(
                    onClick = { navController.navigate(Routes.AllProductScreen) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.Crimson)
                    ),
                ) {
                    Text(
                        text = "Start Shopping",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }


        } else {
            when {
                cartState.isLoading -> {
                    LoadingIndicator()
                }

                cartState.error != null -> {
                    FailureComponent { }
                }

                else -> {
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(cartState.cartItems) { cartItem ->
                            CartCard(
                                onIncrease = {
                                    cartViewModel.updateQuantity(
                                        cartItem.product.id,
                                        cartItem.quantity + 1
                                    )
                                },
                                onDecrease = {
                                    if (cartItem.quantity > 1)
                                        cartViewModel.updateQuantity(
                                            cartItem.product.id,
                                            cartItem.quantity - 1
                                        ) else {
                                        cartViewModel.removeFromCart(cartItem.product.id)
                                    }

                                },
                                onDelete = {
                                    Toast.makeText(
                                        context,
                                        "Removed\uD83D\uDDD1\uFE0F",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    cartViewModel.removeFromCart(cartItem.product.id)

                                },
                                cartItem = cartItem,
                                quantity = cartItem.quantity.toString()
                            )


                        }
                        item {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .background(color = colorResource(R.color.Silver))
                                        .fillMaxWidth()
                                        .size(48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row {
                                        Text(
                                            "Fast Delivery",
                                            fontSize = 28.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = colorResource(R.color.WhiteSmoke),
                                            fontStyle = FontStyle.Italic
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Icon(
                                            imageVector = Icons.Filled.AirportShuttle,
                                            contentDescription = null,
                                            tint = colorResource(R.color.WhiteSmoke),
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .size(32.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {


                                    Text(
                                        text = "Total: ₹${cartState.totalPrice}",
                                        modifier = Modifier.padding(horizontal = 4.dp),
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 20.sp,
                                        color = colorResource(R.color.Green)
                                    )

                                    Button(
                                        onClick = {
                                            navController.navigate(Routes.DeliveryScreen)
                                        },
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = colorResource(
                                                R.color.Crimson
                                            )
                                        )
                                    ) {
                                        Text(
                                            text = "Place Order",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }


                                }
                                Divider(modifier = Modifier.padding(vertical = 8.dp))

                            }

                        }
                    }


                }
            }
        }
    }
}