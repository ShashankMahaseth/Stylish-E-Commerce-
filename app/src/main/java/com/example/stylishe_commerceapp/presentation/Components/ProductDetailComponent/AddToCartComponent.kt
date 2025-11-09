package com.example.stylishe_commerceapp.presentation.Components.ProductDetailComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
import io.ktor.http.parameters

@Composable
@Preview(showSystemUi = true)
fun AddToCartComponent(){
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.group_33816),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(R.drawable.group_33815__1_),
            contentDescription = null
        )

    }
}