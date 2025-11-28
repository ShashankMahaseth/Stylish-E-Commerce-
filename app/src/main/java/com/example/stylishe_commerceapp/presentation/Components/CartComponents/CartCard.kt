package com.example.stylishe_commerceapp.presentation.Components.CartComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.domain.model.CartItem
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.Rating
import kotlin.math.round

@Composable
fun CartCard(onDecrease: () -> Unit , onIncrease: () -> Unit,onDelete: () -> Unit,cartItem: CartItem,quantity:String) {
    val context=LocalContext.current
    val price = (cartItem.product.price ?: 0.0) * 70.0
    val originalPrice = round(price*10) /10
    val discountedPrice = originalPrice * (1 - (cartItem.product.discountPercentage?.div(100.0) ?: 0.0))
    val originalDiscountedPrice= round(discountedPrice*10) /10
    val discount =round(cartItem.product.discountPercentage?.times(10)?:0.0)/10
    val rating = cartItem.product.rating ?: 0.0
    val roundedRating =round(rating*10)/10

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(colorResource(R.color.Snow))
    ) {
        Column {

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(cartItem.product.thumbnail).build(),
                    contentDescription = null,
                    modifier = Modifier.size(82.dp).background(color = colorResource(R.color.Bisque))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    cartItem.product.title?.let {
                        Text(
                            text = it,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                        Rating(roundedRating)

                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(
                            text = "₹$originalPrice",
                            fontSize = 18.sp,
                            color = colorResource(R.color.silver),
                            textDecoration = TextDecoration.LineThrough,
                            fontWeight = FontWeight.SemiBold

                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "₹$originalDiscountedPrice(${discount}%)",
                            fontSize = 20.sp,
                            color = colorResource(R.color.Green),
                            fontWeight = FontWeight.SemiBold

                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Box(modifier = Modifier.background(colorResource(R.color.Lavender))) {
                        Row {
                            TextButton(onClick = onDecrease) {
                                Text(
                                    text = "-",
                                    fontSize = 32.sp,
                                    color = colorResource(R.color.Crimson)
                                )
                            }
                            Text(
                                text = quantity,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            TextButton(onClick = onIncrease) {
                                Text(
                                    text = "+",
                                    fontSize = 24.sp,
                                    color = colorResource(R.color.Crimson)
                                )
                            }
                        }

                    }
                }


            }
            Divider()

            IconButton(
                onClick = onDelete,
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = colorResource(R.color.Silver)
                    )
                    Text(
                        text = "Remove",
                        fontSize = 18.sp,
                        color = colorResource(R.color.silver),
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }


        }

    }
}