package com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.forgetpage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.AuthScreen.AuthScreen
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel

@Composable
fun ForgotPage(navController: NavHostController,authViewModel: AuthViewModel) {
    AuthScreen(
        isSignUp = false,
        authText = "Submit"
        ,
        authText2 ="forgot",
        topText1 = "Forgot",
        topText2 = "Password?",
        isForgot = true,
        onNavForgetClick = {},
        navController,
        authViewModel
    )
}