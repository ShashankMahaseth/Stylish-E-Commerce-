package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R

@Composable
fun HomeSearchBar(value: String, onValueChanged:(String)-> Unit, readonly: Boolean){

    TextField(
        value = value,
        onValueChange =onValueChanged,
        readOnly = readonly,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",


            )
        },

        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .size(48.dp)
            .shadow(4.dp, shape = RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = colorResource(R.color.Snow),
            focusedContainerColor = colorResource(R.color.Snow),
            cursorColor = colorResource(R.color.Crimson),
            focusedLeadingIconColor = colorResource(R.color.Crimson),
            unfocusedLeadingIconColor = colorResource(R.color.silver),
             focusedTextColor = colorResource(R.color.Crimson)
            ),

        )
}