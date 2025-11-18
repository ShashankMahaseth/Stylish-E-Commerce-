package com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.presentation.ViewModel.FavoriteViewModel

@Composable
fun FavoriteIcon(favoriteViewModel: FavoriteViewModel,product: Product){
    val state by favoriteViewModel.state.collectAsState()
    val isFavorite = state.filteredProducts.contains(product)//contains is boolean
    IconButton(onClick = {
    if(isFavorite){
        favoriteViewModel.removeFavoriteProduct(product.id)
    }else{
        favoriteViewModel.addFavoriteProduct(product)
    }

    }) {
        Icon(
            imageVector = if(!isFavorite)Icons.Default.FavoriteBorder else Icons.Default.Favorite,
            contentDescription = null,
            tint =if(!isFavorite) colorResource(R.color.Silver) else colorResource(R.color.Crimson)
        )
    }
}
