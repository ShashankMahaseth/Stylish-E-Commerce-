package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.HomePage.HomeScreen
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.common.HomeSearchBar
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@Composable
fun HomePage(productViewModel: ProductViewModel,navController: NavController) {
    val productState by productViewModel.products.collectAsState()
    var search by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        productViewModel.getAllProducts()
    }
    HomeScreen(navController) {

        when (val state = productState) {
            is com.example.stylishe_commerceapp.core.utils.Result.Loading -> {
                LoadingIndicator()
            }

            is com.example.stylishe_commerceapp.core.utils.Result.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(8.dp)
                ) {

                    item(span = { GridItemSpan(2) }) {
                        Column {

                            Box(
                                modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    onClick = {

                                        navController.navigate(Routes.SearchScreen)
                                    }
                                )) {
                                HomeSearchBar(
                                    value = search,
                                    onValueChanged = { search = it },
                                    readonly = false,

                                    )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "All Featured",
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp
                            )
                            LazyRow(modifier = Modifier.background(color = colorResource(R.color.Snow))) {
                                items(categoryList) { category ->
                                    HomeCategory(navController)
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            Banner()
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyRow {
                                items(
                                    state.data.products.filter {
                                        it.category != "groceries" &&
                                                it.category != "home-decoration"
                                                && it.category != "kitchen-accessories"
                                                && it.category != "motorcycle"
                                                && it.category != "sports-accessories"
                                                && it.category != "vehicle"
                                                && it.category != "furniture"
                                    }.take(10)
                                ) { products ->
                                    ProductCard(
                                        thumbnail = products.thumbnail,
                                        title = products.title,
                                        productList = products
                                    ) {
                                        navController.navigate(
                                            Routes.ProductDetailScreen(
                                                productId = products.id ?: 0
                                            )
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Image(
                                painter = painterResource(R.drawable.mac),
                                contentDescription = null,
                                modifier = Modifier.clickable(
                                    onClick = {
                                        navController.navigate(
                                            Routes.AllCommonProductScreen(
                                                listOf("womens-shoes")
                                            )
                                        )
                                    }, indication = null,
                                    interactionSource = remember { MutableInteractionSource() })
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }


                    }
                    items(state.data.products.filter {
                        it.category != "groceries" &&
                                it.category != "home-decoration"
                                && it.category != "kitchen-accessories"
                                && it.category != "motorcycle"
                                && it.category != "sports-accessories"
                                && it.category != "vehicle"
                                && it.category != "furniture"
                    }
                        .shuffled()
                        .take(26)
                    ) { products ->
                        ProductCard(
                            thumbnail = products.thumbnail,
                            title = products.title,

                            productList = products
                        ) {
                            navController.navigate(
                                Routes.ProductDetailScreen(
                                    productId = products.id ?: 0
                                )
                            )
                        }
                    }

                    item(span = { GridItemSpan(2) }) {
                        Column {
                            MoreItemComponent() {//onClicked

                                navController.navigate(Routes.AllProductScreen)

                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            ShoesCard {//onShoesClick
                                navController.navigate(Routes.AllCommonProductScreen(listOf("mens-shoes")))

                            }
                            Spacer(modifier = Modifier.height(16.dp))

                        }
                    }
                }
            }

            is Result.Failure -> {
                FailureComponent {
                    productViewModel.reset()
                }
            }

            else -> {
                Text(
                    text = "Loading"
                )
            }

        }
    }
}