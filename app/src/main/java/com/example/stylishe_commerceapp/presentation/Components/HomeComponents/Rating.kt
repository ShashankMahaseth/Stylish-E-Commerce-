package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showSystemUi = true)
fun Rating() {
val rating=5.0
    Row (){
    for (i in 1..5){

            Icon(
                imageVector = if(i <rating)Icons.Filled.StarRate else Icons.Filled.StarBorder,
                contentDescription = null
            )
        }
        Text(
            text = "$rating",
            modifier=Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier=Modifier.width(2.dp))
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null,
            )

    }
}