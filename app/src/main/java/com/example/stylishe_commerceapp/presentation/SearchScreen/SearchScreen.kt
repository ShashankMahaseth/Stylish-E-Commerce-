package com.example.stylishe_commerceapp.presentation.SearchScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.ProductCard
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel
import com.example.stylishe_commerceapp.presentation.common.HomeSearchBar
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(searchViewModel: ProductViewModel, navController: NavController){
    val searchState by searchViewModel.searchProduct .collectAsState()
    var search by remember { mutableStateOf("") }
    val focusRequester=remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()  // help to focus on search bar
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    HomeSearchBar(value=search, onValueChanged = {search=it}, readonly = true)
                    searchViewModel.searchProduct(search)
                },
                colors = TopAppBarDefaults
                    .topAppBarColors(colorResource(R.color.LightPink)),

            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.WhiteSmoke)
    ) { innerPadding->
        Box (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),

        ){
            when(val state =searchState) {
                is Result.Idle -> {
                    Text(
                        text = "Please Search Items",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.silver),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Result.Loading->{
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LoadingIndicator()
                    }
                }

                is Result.Success->{
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        if(search.isNotEmpty()) {
                            items(
                                state.data.products.filter {
                                    it.category != "groceries" &&
                                            it.category != "home-decoration"
                                            && it.category != "kitchen-accessories"
                                            && it.category != "motorcycle"
                                            && it.category != "sports-accessories"
                                            && it.category != "vehicle"
                                            && it.category != "furniture"
                                }
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
                        }else{
                            item(span = { GridItemSpan(2)}) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(
                                    text = "Please Search Items",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(R.color.silver),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                                }

                        }

                    }
                }


                is Result.Failure->{
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column {
                            Icon(
                                painter = painterResource(R.drawable.wifi_slash),
                                contentDescription = null,
                                modifier = Modifier.size(72.dp).align(Alignment.CenterHorizontally),
                            )
                            Text(
                                text = "Please Check Internet Connection",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = colorResource(R.color.silver),
                            )
                        }

                    }
                }



            }

        }


    }
}