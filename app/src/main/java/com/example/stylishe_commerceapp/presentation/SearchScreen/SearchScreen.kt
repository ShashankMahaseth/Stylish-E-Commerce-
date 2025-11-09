package com.example.stylishe_commerceapp.presentation.SearchScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.common.HomeSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(){
    var search by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    HomeSearchBar(search, onValueChanged = {search=it}, readonly = true)
                },
                colors = TopAppBarDefaults
                    .topAppBarColors(colorResource(R.color.LightPink)),

            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.WhiteSmoke)
    ) { innerPadding->
        Box (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Please Search Items",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.silver),
                modifier = Modifier
            )

        }


    }
}