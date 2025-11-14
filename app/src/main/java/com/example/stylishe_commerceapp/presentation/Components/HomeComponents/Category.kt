package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.lazy.items


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel

@Composable
fun Category(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center

    ) {
        items(categoryList){ category ->

                Box (modifier = Modifier.padding(16.dp)){
                    // Title below the icon
                    Box(modifier=Modifier.fillMaxWidth().border(1.dp,
                        color=colorResource(R.color.silver),
                        shape =  RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp)
                        ).size(90.dp).
                        clickable(
                            onClick = {

                            when (category.image) {
                                categoryList[0].image -> navController.navigate(
                                    Routes.AllCommonProductScreen(
                                        listOf("beauty", "skin-care")
                                    )
                                )

                                categoryList[1].image -> navController.navigate(
                                    Routes.AllCommonProductScreen(
                                        listOf(
                                            "womens-shoes",
                                            "mens-shoes",
                                            "mens-watches",
                                            "sunglasses",
                                            "tops",
                                            "womens-jewellery",
                                            "womens-shoes",
                                            "womens-watches"
                                        )
                                    )
                                )

                                categoryList[2].image -> navController.navigate(
                                    Routes.AllCommonProductScreen(
                                        listOf(
                                            "smartphones", "laptops", "tablets",
                                            "mobile-accessories"
                                        )
                                    )
                                )

                                categoryList[3].image -> navController.navigate(
                                    Routes.AllCommonProductScreen(
                                        listOf("womens-dresses", "tops")
                                    )
                                )

                                categoryList[4].image -> navController.navigate(
                                    Routes.AllCommonProductScreen(
                                        listOf("mens-shirts", "mens-shoes")
                                    )
                                )

                            }
                        },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )

                    ){
                        Text(
                            text = category.title,
                            modifier = Modifier.padding(top = 4.dp).align(Alignment.Center),
                            color = colorResource(R.color.black), // adjust color if needed
                            fontSize = 28.sp,
                            maxLines = 1,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,

                            )
                    }
                    Image(
                        painter = painterResource(category.image),
                        contentDescription = category.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(92.dp).clip(CircleShape)
                    )




            }


        }
    }
}

data class CategoryItemsList(
    val image:Int,
    val title:String
)
val categoryItemList =listOf(
    CategoryItemsList(R.drawable.ellipse_4,"Beauty"),
    CategoryItemsList(R.drawable.unsplash__3q3tsj01nc,"Fashion"),
    CategoryItemsList(R.drawable.electronics,"Electronics"),
    CategoryItemsList(R.drawable.unsplash_oyye4g_i5zq,"Women's"),
    CategoryItemsList(R.drawable.unsplash_xpjyl0l5ii8,"Mens")
)
