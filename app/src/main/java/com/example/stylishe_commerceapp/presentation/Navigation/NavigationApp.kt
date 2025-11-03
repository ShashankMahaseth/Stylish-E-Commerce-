package com.example.stylish.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.forgetpage.ForgotPage
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.loginpage.LoginScreen
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.signuppage.SignUpScreen
import com.example.stylishe_commerceapp.presentation.AuthPage.SplashScreen.SplashScreen
import com.example.stylishe_commerceapp.presentation.AuthPage.onboarding.OnBoardingScreen
import com.example.stylishe_commerceapp.presentation.HomePage.HomeScreen

import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel
import com.example.stylishe_commerceapp.presentation.ViewModel.UserPreferencesViewModel

@Composable
fun NavigationApp() {
    val context = LocalContext.current

    val viewModel: AuthViewModel = viewModel()

    val userPreferencesViewModel: UserPreferencesViewModel= viewModel()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Splash) {
        composable<Routes.Splash> {
            SplashScreen(navController,userPreferencesViewModel)
        }
        composable<Routes.Onboarding> {
            OnBoardingScreen(navController)
        }
        composable<Routes.Login> {
            LoginScreen(
                navController,
                viewModel,
            )
        }
        composable<Routes.SignUp> {
            SignUpScreen(navController, viewModel)
        }
        composable<Routes.Forgot> {
            ForgotPage(navController,viewModel)
        }
        composable<Routes.Home> {
            // Disable back button on HomeScreen
          //  BackHandler(enabled = true) { }

            HomeScreen()
        }
    }
}

