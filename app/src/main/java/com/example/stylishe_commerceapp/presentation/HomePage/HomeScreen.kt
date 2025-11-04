package com.example.stylishe_commerceapp.presentation.HomePage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.HomeSearchBar
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.HomeTopAppBar

@Composable
fun HomeScreen(){
    var search by remember { mutableStateOf("") }
    Scaffold(modifier=Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.Snow),
        topBar = {
            HomeTopAppBar(onListClick = {}){//onProfileClick

            }
        }
    ) {innerPadding->
        Box(modifier = Modifier.padding(innerPadding).padding(16.dp)){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {

                item(span = {GridItemSpan(2)}) {
                    Column {

                        HomeSearchBar(
                            value = search, onValueChanged = { search = it },
                            readonly = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "All Featured",
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                        LazyRow {
                            item {

                            }
                        }
                    }


                }
            }

        }

    }

}