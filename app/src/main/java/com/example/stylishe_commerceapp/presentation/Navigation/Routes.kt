package com.example.stylishe_commerceapp.presentation.Navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Splash: Routes()
    @Serializable
    data object Onboarding : Routes()

    @Serializable
    data object Login:Routes()
    @Serializable
    data object SignUp:Routes()

    @Serializable
    data object Forgot:Routes()

    @Serializable
    data object Home: Routes()
}