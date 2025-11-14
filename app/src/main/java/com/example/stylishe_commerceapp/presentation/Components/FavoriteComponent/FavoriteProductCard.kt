package com.example.stylishe_commerceapp.presentation.Components.FavoriteComponent

import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.Rating
import com.example.stylishe_commerceapp.data.remote.Product
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.common.ShareComponent
import kotlin.math.round


@Composable
fun FavoriteProductCard(thumbnail: String?, title: String?, productList:Product, onDelete:()-> Unit, onProductClick:()-> Unit) {
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




    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(4.dp)
            .shadow(elevation = 3.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black,
                spotColor = Color.Black
            ).size(width = 200.dp, height = 300.dp),
        colors = CardDefaults
            .cardColors(colorResource(R.color.Snow)),
        onClick = onProductClick
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.fillMaxWidth()){
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = colorResource(R.color.Crimson)
                    )
                }
            }
            AsyncImage(
                model= ImageRequest
                    .Builder(context)
                    .data(thumbnail).build()
                ,
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = title?:"no text",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Text(
                text = "₹ $originalDiscountedPrice(${discount}% Off)",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = colorResource(R.color.green)
            )
            Text(
                text = "₹ $originalPrice",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colorResource(R.color.Silver),
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                Rating(roundedRating)
                ShareComponent {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share Product"))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}


