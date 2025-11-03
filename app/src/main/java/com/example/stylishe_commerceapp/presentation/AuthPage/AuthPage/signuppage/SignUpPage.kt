package com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.signuppage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.AuthScreen.AuthScreen
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel

@Composable
fun SignUpScreen(navController: NavHostController,authViewModel: AuthViewModel) {
    AuthScreen(
        isSignUp = true, authText = "SignUp",
        authText2 = "Login",
        topText1 = "Create an ", topText2 = "account",
        isForgot = false,
        onNavForgetClick = {},
        navController=navController,
       authViewModel
    )
}