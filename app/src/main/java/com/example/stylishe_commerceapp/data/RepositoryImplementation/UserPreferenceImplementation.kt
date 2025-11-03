package com.example.stylishe_commerceapp.data.RepositoryImplementation

import com.example.stylishe_commerceapp.data.local.UserPreferencesDataStore
import com.example.stylishe_commerceapp.domain.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// connection with Data layer and Domain layer to each other in userPreference
class UserPreferenceImplementation @Inject constructor(private val userPreferencesDataStore: UserPreferencesDataStore):
    UserPreferenceRepository {
    override val isFirstTimeLogin: Flow<Boolean> = userPreferencesDataStore.isFirstTimeLogin
    override val isLoggedIn: Flow<Boolean> = userPreferencesDataStore.isLoggedIn
    override suspend fun setFirstTimeLogin(isFirstTime: Boolean){
        userPreferencesDataStore.setFirstTimeLogin(isFirstTime)
    }

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferencesDataStore.setLoginIn(isLoggedIn)
    }



}