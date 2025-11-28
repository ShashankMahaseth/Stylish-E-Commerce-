package com.example.stylishe_commerceapp.presentation.HomePage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.FilterComponent
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.ProductCard
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.common.HomeSearchBar
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator
import kotlin.collections.sortedBy
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllProductScreen(navController: NavController, productViewModel: ProductViewModel) {
    val productState by productViewModel.allProducts.collectAsState()
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var selectedSort by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        productViewModel.getCompleteProducts()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
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
                            value = "",
                            onValueChanged = { },
                            readonly = false,

                            )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.LightPink))
            )
        },
        containerColor = colorResource(R.color.WhiteSmoke)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {


            when (val state = productState) {
                is Result.Loading -> {
                    LoadingIndicator()
                }

                is Result.Success -> {

                    val filteredProducts = state.data.products.filter {
                        val excluded = listOf(
                            "groceries", "home-decoration", "kitchen-accessories",
                            "motorcycle", "sports-accessories", "vehicle",
                            "furniture"
                        )
                        it.category !in excluded &&
                                (selectedCategory == null || it.category in when (selectedCategory) {
                                    "beauty" -> listOf("beauty", "fragrances", "skin-care")
                                    "fashion" -> listOf(
                                        "womens-shoes",
                                        "mens-shoes",
                                        "mens-watches",
                                        "sunglasses",
                                        "tops",
                                        "womens-jewellery",
                                        "womens-shoes",
                                        "womens-watches"
                                    )

                                    "electronics" -> listOf(
                                        "smartphones",
                                        "laptops",
                                        "tablets",
                                        "mobile-accessories"
                                    )

                                    "women's" -> listOf("womens-dresses", "tops")
                                    else -> listOf("mens-shirts", "mens-shoes")
                                }
                                        )
                    }.shuffled()
                    val finalProducts = when (selectedSort) {
                        "low to heigh" -> filteredProducts.sortedBy {
                            getFinalPrice(it.price, it.discountPercentage)
                        }


                        "heigh to low" -> filteredProducts.sortedByDescending {
                            getFinalPrice(it.price, it.discountPercentage)

                        }

                        else -> filteredProducts
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        item(span = { GridItemSpan(2) }) {
                            FilterComponent(
                                onFilterSelected = { cat ->
                                    selectedCategory = cat
                                },
                                onSortSelected = { short ->
                                    selectedSort = short

                                }
                            )
                        }
                        items(finalProducts) { products ->


                            ProductCard(
                                thumbnail = products.thumbnail,
                                title = products.title,
                                productList = products
                            ) {
                                navController.navigate(
                                    Routes.ProductDetailScreen(
                                        productId = products.id
                                    )
                                )
                            }
                        }
                    }

                }

                is Result.Failure -> {
                    FailureComponent {
                        productViewModel.refresh()
                    }

                }

                else -> {}
            }


        }

    }


}

fun getFinalPrice(original: Double?, discount: Double?): Double {
    val price = original ?: 0.0
    val discountPercentage = discount ?: 0.0

    val originalPrice =round(price * 10) / 10
    val discountedPrice = originalPrice * (1 - (discountPercentage / 100.0))
    return round(discountedPrice * 10) / 10
}