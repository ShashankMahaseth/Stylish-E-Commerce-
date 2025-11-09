package com.example.stylishe_commerceapp.presentation.ProductDetailsScreen

import Product
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.data.remote.ProductDto
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.ProductDetailComponent
import com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent.ProductDetailTopAppBar
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@Composable
fun ProductDetailsScreen(viewModel: ProductViewModel,productId:Int,navController: NavController) {
    val state by viewModel.products.collectAsState()

    when (val state = state) {

        is Result.Success -> state.data.products.find { it.id == productId }
        else -> null

    }
    Scaffold(

        topBar = {
            ProductDetailTopAppBar(navController)
        },
        containerColor = colorResource(R.color.WhiteSmoke)
    ) {innerPadding->

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {

            item {
                when (val state = state) {
                    is Result.Loading -> {
                        LoadingIndicator()
                    }

                    is Result.Success -> {
                        val product = state.data.products.find { it.id == productId }


                        if (product != null) {
                            ProductDetailComponent(product = product)
                        } else {
                            FailureComponent {
                                viewModel.reset()
                            }
                        }

                    }

                    is Result.Failure -> {
                        FailureComponent() {
                            viewModel.reset()
                        }
                    }

                    else -> {}
                }

            }
        }
    }
}