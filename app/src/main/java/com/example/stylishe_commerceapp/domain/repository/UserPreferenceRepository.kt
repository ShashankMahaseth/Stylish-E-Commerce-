package com.example.stylishe_commerceapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    val isFirstTimeLogin: Flow<Boolean>
    val isLoggedIn : Flow<Boolean>
    suspend fun setFirstTimeLogin(isFirstTime: Boolean)
    suspend fun setLoggedIn(isLoggedIn: Boolean)
}