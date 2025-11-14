package com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent

import com.example.stylishe_commerceapp.data.remote.Product
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
import kotlin.math.round

@Composable
fun ProductDetailShareComponent(productList: Product,title:String?) {
    val context=LocalContext.current
    val price = (productList.price ?: 0.0) * 70.0
    val originalPrice = round(price*10) /10
    val discountedPrice = originalPrice * (1 - (productList.discountPercentage?.div(100.0) ?: 0.0))
    val originalDiscountedPrice= round(discountedPrice*10) /10
    val discount =round(productList.discountPercentage?.times(10)?:0.0)/10
    val rating = productList.rating ?: 0.0
    val roundedRating =round(rating*10)/10
    val shareText =
        "Check out this product: $title \n Brand:${productList.brand}" +
                "\n" +
                " $originalDiscountedPrice(${discount}%) " +
                "\n Rating: $roundedRating \n " +
                "Description: ${productList.description}"
    Column {
        IconButton(onClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share Product"))
        }) {

                Icon(
                    painter = painterResource(R.drawable.share),
                    contentDescription = null,
                    tint = colorResource(R.color.Crimson),
                    modifier = Modifier.size(24.dp)
                )

        }
        Text(
            text = "Share"
        )
    }
}
