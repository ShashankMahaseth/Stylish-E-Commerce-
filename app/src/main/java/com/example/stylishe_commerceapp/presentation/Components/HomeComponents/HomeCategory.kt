package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel

@Composable
fun HomeCategory(navController: NavController) {
    LazyRow(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // space between items
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(categoryList){ category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = {
                        when(category.image){
                            categoryList[0].image->navController.navigate(Routes.AllCommonProductScreen(listOf("beauty","skin-care")))
                            categoryList[1].image->navController.navigate(Routes.AllCommonProductScreen(listOf("womens-shoes","mens-shoes"
                                ,"mens-watches",
                                "sunglasses","tops", "womens-jewellery","womens-shoes", "womens-watches")))
                            categoryList[2].image->navController.navigate(Routes.AllCommonProductScreen(listOf("smartphones","laptops"
                                ,"tablets",
                                "mobile-accessories")))

                            categoryList[3].image->navController.navigate(Routes.AllCommonProductScreen(listOf("womens-dresses","tops")))
                            categoryList[4].image->navController.navigate(Routes.AllCommonProductScreen(listOf("mens-shirts","mens-shoes")))

                        }

                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(72.dp)
                        .padding(2.dp)
                ) {
                    Image(
                        painter = painterResource(category.image),
                        contentDescription = category.title,
                        contentScale = ContentScale.Crop
                    )
                }

                // Title below the icon
                Text(
                    text = category.title,
                    modifier = Modifier.padding(top = 4.dp),
                    color = colorResource(R.color.black) // adjust color if needed
                )
            }
        }
    }
}

    data class CategoryItems(
    val image:Int,
    val title:String
    )
val categoryList =listOf(
    CategoryItems(R.drawable.ellipse_4,"Beauty"),
    CategoryItems(R.drawable.unsplash__3q3tsj01nc,"Fashion"),
    CategoryItems(R.drawable.electronics,"Electronics"),
    CategoryItems(R.drawable.unsplash_oyye4g_i5zq,"Women's"),
    CategoryItems(R.drawable.unsplash_xpjyl0l5ii8,"Mens")
)
