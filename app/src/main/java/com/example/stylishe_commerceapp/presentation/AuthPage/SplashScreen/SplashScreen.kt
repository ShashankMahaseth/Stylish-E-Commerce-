package com.example.stylishe_commerceapp.presentation.AuthPage.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.UserPreferencesViewModel

import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController,userPreferenceViewModel: UserPreferencesViewModel) {
    val state by userPreferenceViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        delay(1000)
        when {
            state.isLoggedIn ->
                navController.navigate(Routes.Home) {
                    popUpTo(Routes.Splash) { inclusive = true }
                }
            state.isFirstTimeLogin->{
                navController.navigate(Routes.Onboarding)
            }
            else->{
                navController.navigate(Routes.Login){
                    popUpTo(Routes.Splash){inclusive=true}
                }
            }

        }
    }
    Scaffold (){innerPadding->
        Column (modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(R.drawable.logoipsum_255_1),
                contentDescription = "Stylish",
                modifier = Modifier.size(width = 275.dp, height = 100.dp)
            )

        }

    }

}