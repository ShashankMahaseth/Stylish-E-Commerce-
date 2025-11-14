package com.example.stylishe_commerceapp.presentation.Favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Components.FavoriteComponent.FavoriteProductCard
import com.example.stylishe_commerceapp.presentation.HomePage.HomeScreen
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.FavoriteViewModel
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@Composable
fun FavoritePage(navController: NavController, favoriteViewModel: FavoriteViewModel) {
    val state by favoriteViewModel.state.collectAsState()

    HomeScreen(navController) {
        Column {

            Text(
                text = "Your Favorite Products",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = colorResource(R.color.Crimson),
                modifier = Modifier.padding(8.dp)

            )
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                color = colorResource(R.color.Silver)
            )




            when {

                state.isLoading -> {
                    LoadingIndicator()
                }


                state.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,

                        ) {
                        Text(
                            text = "Please Connect to Your Internet",
                            fontSize = 32.sp
                        )
                    }

                }

                state.filteredProducts.isEmpty() && state.allProducts.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column {
                            Text(
                                text = "No Favorite Product Found",
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                color = colorResource(R.color.Silver),
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    navController.navigate(Routes.AllProductScreen)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(R.color.Crimson)
                                ),
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "Browse Products",
                                    fontSize = 24.sp,

                                )
                            }
                        }
                    }
                }

                else -> {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        items(state.filteredProducts) { product ->
                            FavoriteProductCard(
                                thumbnail = product.thumbnail,
                                title = product.title,
                                productList = product,
                                onDelete = {
                                    favoriteViewModel.removeFavoriteProduct(product.id ?: 0)
                                }
                            ) {
                                navController.navigate(Routes.ProductDetailScreen(product.id))
                            }

                        }
                    }

                }
            }


        }
    }
}