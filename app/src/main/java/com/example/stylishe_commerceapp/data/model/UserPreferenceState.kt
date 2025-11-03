package com.example.stylishe_commerceapp.data.model

data class UserPreferenceState(
    val isFirstTimeLogin: Boolean = true,
    val isLoggedIn: Boolean = false,
    val isLoading:Boolean  =  true
)

