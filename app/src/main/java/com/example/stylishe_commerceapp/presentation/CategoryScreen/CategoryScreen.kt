package com.example.stylishe_commerceapp.presentation.CategoryScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.Category
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.HomeCategory
import com.example.stylishe_commerceapp.presentation.HomePage.HomeScreen

@Composable
fun CategoryScreen(navController: NavController){
    HomeScreen(navController) {

                Category(navController)


        }

    }
