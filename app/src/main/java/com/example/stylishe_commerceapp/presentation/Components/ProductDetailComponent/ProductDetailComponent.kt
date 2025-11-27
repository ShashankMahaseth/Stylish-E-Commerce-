package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import com.example.stylishe_commerceapp.data.remote.Product
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent.AddToCartComponent
import com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent.FavoriteIcon
import com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent.ProductDetailShareComponent
import com.example.stylishe_commerceapp.presentation.ViewModel.CartViewModel
import com.example.stylishe_commerceapp.presentation.ViewModel.FavoriteViewModel
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.delay
import kotlin.math.round

@Composable
fun ProductDetailComponent(product: Product,favoriteViewModel: FavoriteViewModel,cartViewModel: CartViewModel= hiltViewModel()) {
    val context=LocalContext.current
    val price = (product.price ?: 0.0) * 70.0
    val originalPrice = round(price*10) /10
    val discountedPrice = originalPrice * (1 - (product.discountPercentage?.div(100.0) ?: 0.0))
    val originalDiscountedPrice= round(discountedPrice*10) /10
    val discount =round(product.discountPercentage?.times(10)?:0.0)/10
    val rating = product.rating ?: 0.0
    val roundedRating =round(rating*10)/1
    val images = product.images?.takeIf { it.isNotEmpty() } ?: emptyList()

    val pagerState = rememberPagerState(pageCount = { images.size })
    0

    LaunchedEffect(pagerState.pageCount) {
        while (true) {
            delay(5000) // delay between slides (3 seconds)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }


    Column(modifier = Modifier.padding(8.dp)) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

        ProductDetailShareComponent(

            productList = product,
            title = product.title,
        )
    }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier

                    .fillMaxWidth()
                    .size(350.dp)
                    .clip(shape = RoundedCornerShape(8.dp))

            ) { page ->
                    val images = images[page]
                AsyncImage(
                   model = ImageRequest.Builder(context)
                       .data(images)
                       .crossfade(true)
                       .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            DotsIndicator(
                dotCount = pagerState.pageCount,
                type = ShiftIndicatorType(
                    dotsGraphic = DotGraphic(
                        color = colorResource(R.color.HotPink),
                        size = 8.dp
                    )
                ),
                pagerState = pagerState,
                )
            Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Brand:${product.brand ?: "No Brand"}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.Crimson)
            )
            FavoriteIcon(favoriteViewModel, product = product)
        }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${product.title}",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.Blue)
            )


            Spacer(modifier = Modifier.height(8.dp))
        product.description?.let {
            Text(
                text =it,
                fontSize = 14.sp,
                color = colorResource(R.color.Black)
            )
        }
            Spacer(modifier = Modifier.height(8.dp))

            Rating(rating = 3.5)
        Row {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "₹ $originalPrice",
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.silver),
                textDecoration = TextDecoration.LineThrough,
                fontSize = 20.sp,

                )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "₹ $originalDiscountedPrice($discount% Off)",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Stock: inStock(${product.stock})",
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.green)
        )
        Spacer(modifier = Modifier.height(8.dp))

        AddToCartComponent(cartViewModel = cartViewModel,product = product)





    }
}

