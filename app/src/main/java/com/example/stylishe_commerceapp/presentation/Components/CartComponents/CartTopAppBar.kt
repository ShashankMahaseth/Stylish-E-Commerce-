package com.example.stylishe_commerceapp.presentation.Components.CartComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopAppBar(onBackClick: () -> Unit){

    CenterAlignedTopAppBar(
        title = {
            Text(
                text ="Cart Items",
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.
        topAppBarColors(containerColor = colorResource(R.color.LightPink)),
        expandedHeight = 48.dp
    )
}