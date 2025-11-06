package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@Composable
fun HomeCategory() {
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // space between items
        verticalAlignment = Alignment.CenterVertically
    ) {
        categoryList.forEach { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = {},
                    shape = CircleShape,
                    modifier = Modifier
                        .size(72.dp)
                        .padding(2.dp)
                ) {
                    Image(
                        painter = painterResource(category.image),
                        contentDescription = category.title,
                        contentScale = ContentScale.Crop
                    )
                }

                // Title below the icon
                Text(
                    text = category.title,
                    modifier = Modifier.padding(top = 4.dp),
                    color = colorResource(R.color.black) // adjust color if needed
                )
            }
        }
    }
}

    data class CategoryItems(
    val image:Int,
    val title:String
    )
val categoryList =listOf(
    CategoryItems(R.drawable.ellipse_4,"Beauty"),
    CategoryItems(R.drawable.unsplash__3q3tsj01nc,"Fashion"),
    CategoryItems(R.drawable.electronics,"Electronics"),
    CategoryItems(R.drawable.unsplash_oyye4g_i5zq,"Women's"),
    CategoryItems(R.drawable.unsplash_xpjyl0l5ii8,"Mens")
)
