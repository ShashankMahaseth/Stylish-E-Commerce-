package com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.stylishe_commerceapp.R

@Composable
fun FavoriteIcon(){
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = colorResource(R.color.Crimson)
        )
    }
}
