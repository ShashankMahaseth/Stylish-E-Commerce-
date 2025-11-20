package com.example.stylishe_commerceapp.presentation.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.Banner
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.HomeCategory
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.HomePage
import com.example.stylishe_commerceapp.presentation.common.HomeSearchBar
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.HomeTopAppBar
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.MoreItemComponent
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.ProductCard
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.ShoesCard
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.categoryList
import com.example.stylishe_commerceapp.presentation.Favorite.FavoritePage
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel
import com.example.stylishe_commerceapp.presentation.common.BottomNavigationBar
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator

@Composable
fun HomeScreen(navController: NavController,home:@Composable ()-> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.WhiteSmoke),
        topBar = {
            HomeTopAppBar(onListClick = {}){
                navController.navigate(Routes.SettingScreen)
            }

        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            home()
        }

    }
}