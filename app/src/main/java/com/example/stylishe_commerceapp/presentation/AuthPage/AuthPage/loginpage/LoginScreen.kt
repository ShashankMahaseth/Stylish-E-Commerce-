package com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.loginpage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.AuthScreen.AuthScreen
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel


@Composable
fun LoginScreen(navController: NavHostController,authViewModel: AuthViewModel) {
    AuthScreen(isSignUp = false,
        authText = "Login", authText2 = "SignUp",

        topText1 = "Welcome", topText2 = "Back!",
        isForgot = false,
        onNavForgetClick = {},
        navController=navController,
        authViewModel
    )
}