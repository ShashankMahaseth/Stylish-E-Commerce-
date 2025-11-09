package com.example.stylishe_commerceapp.presentation.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun ShareComponent(onSharedClick:()->Unit){
    IconButton(onClick = onSharedClick,
        shape = CircleShape, modifier = Modifier,

        ) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null,
            modifier = Modifier
        )
    }
}