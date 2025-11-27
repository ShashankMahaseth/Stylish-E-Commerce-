package com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.presentation.ViewModel.CartViewModel
import io.ktor.http.parameters

@Composable
fun AddToCartComponent(cartViewModel: CartViewModel,product: Product){
    val context= LocalContext.current
    val state by cartViewModel.cartState.collectAsState()
    val isAlreadyInCart = state.cartItems.any { it.product.id == product.id }
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.group_33816),
            contentDescription = null,
            modifier = Modifier.clickable(
                onClick = {

                    if(isAlreadyInCart){
                        Toast.makeText(context, "⚠\uFE0FAlready in Cart", Toast.LENGTH_SHORT).show()
                        return@clickable
                    }else {
                        cartViewModel.addToCart(product, quantity =1 )
                        Toast.makeText(context, "Added to Cart \uD83D\uDED2", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(R.drawable.group_33815__1_),
            contentDescription = null
        )

    }
}