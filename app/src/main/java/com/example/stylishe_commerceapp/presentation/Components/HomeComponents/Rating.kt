package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
import kotlin.math.floor

@Composable
fun Rating(rating:Double,onSharedClick:()->Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Row() {
            for (i in 1..5) {
                val fullStars =
                    floor(rating).toInt()//returns the largest integer less than or equal to x like 2.9 then 2
                val hasHalfStar = rating % 1 != 0.0
                val icon = when {
                    i <= fullStars -> Icons.Filled.StarRate
                    i == fullStars + 1 && hasHalfStar -> Icons.Filled.StarHalf
                    else -> Icons.Filled.StarBorder
                }

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = when (rating) {
                        in 1.0..2.9 -> colorResource(R.color.DarkRed)
                        in 3.0..3.9 -> colorResource(R.color.DarkGoldenrod)

                        else -> {
                            colorResource(R.color.DarkOliveGreen)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Text(
                text = "$rating",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(2.dp))
            IconButton(onClick = onSharedClick, shape = CircleShape, modifier = Modifier.size(25.dp)) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                    modifier = Modifier.size(23.dp)
                )
            }

        }
    }
}