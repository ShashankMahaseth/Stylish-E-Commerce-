package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(onListClick:()-> Unit, onProfileClick:()-> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.logoipsum_255_1),
                contentDescription = "StylishLogo"
            )
        },
        navigationIcon = {
            IconButton(onClick = onListClick) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "list",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Image(
                    painter = painterResource(R.drawable._289_skvnqsbgqu1pidewmjgtmte2_1),
                    contentDescription = "Profile",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                spotColor = colorResource(R.color.black),
                ambientColor = colorResource(R.color.black)
            ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.LightPink)
        )
    )
}