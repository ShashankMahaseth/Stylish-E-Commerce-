package com.example.stylishe_commerceapp.presentation.HomePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
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
import com.example.stylishe_commerceapp.data.remote.ProductDto
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.common.HomeSearchBar
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.ProductCard
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllProductScreen(navController: NavController, productViewModel: ProductViewModel){
    val productState by productViewModel.products.collectAsState()
    LaunchedEffect(Unit){
        productViewModel.getAllProducts()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .clickable (
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {

                                navController.navigate(Routes.SearchScreen)
                            }
                        )) {
                        HomeSearchBar(
                            value = "",
                            onValueChanged = {  },
                            readonly = false,

                            )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.LightPink))
            )
        },
        containerColor = colorResource(R.color.WhiteSmoke)
    ) {innerPadding->
        Box (modifier = Modifier.fillMaxSize().padding(innerPadding)){



                    when (val state = productState) {
                        is Result.Loading -> {
                                LoadingIndicator()
                            }
                        is Result.Success-> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2)
                            ) {
                                items(state.data.products.filter {

                                    it.category != "groceries" &&
                                            it.category != "home-decoration"
                                            && it.category != "kitchen-accessories"
                                            && it.category != "motorcycle"
                                            && it.category != "sports-accessories"
                                            && it.category != "vehicle"
                                            && it.category != "furniture"
                                }) {products->
                                    ProductCard(
                                        thumbnail = products.thumbnail,
                                        title = products.title,
                                        productList = products
                                    ){
                                        navController.navigate(Routes.ProductDetailScreen(productId = products.id?:0))
                                    }
                                }
                            }

                        }
                        is Result.Failure->{
                            FailureComponent {
                                productViewModel.reset()
                            }

                        }
                        else -> {}
                    }






        }

    }


}