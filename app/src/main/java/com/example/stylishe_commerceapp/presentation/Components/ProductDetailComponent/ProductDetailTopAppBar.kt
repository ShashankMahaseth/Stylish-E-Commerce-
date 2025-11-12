package com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.text.contextmenu.modifier.appendTextContextMenuComponents
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailTopAppBar(navController: NavController){
    CenterAlignedTopAppBar(
       title = {},
       navigationIcon = {
           IconButton(onClick = {navController.popBackStack()}) {//to navigate previous Screen
               Icon(
                   imageVector = Icons.Default.ArrowBackIosNew,
                   contentDescription = null
               )
           }
       },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    tint = colorResource(R.color.Crimson)
                )
            }
        },
        modifier = Modifier.shadow(8.dp),
        colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.LightPink))

    )
}


