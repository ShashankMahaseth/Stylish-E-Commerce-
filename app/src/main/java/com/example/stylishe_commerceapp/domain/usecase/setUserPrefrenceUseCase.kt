package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.domain.repository.UserPreferenceRepository
import javax.inject.Inject


class SetUserPreferenceUseCase @Inject constructor(private val userPreferenceRepository: UserPreferenceRepository){
    suspend fun setFirstTimeLogin(isFirstTime: Boolean){
        //we not use invoke because it Set only one time not again and again
     userPreferenceRepository.setFirstTimeLogin(isFirstTime)
    }
    suspend fun setLoggedIn(isLoggedIn: Boolean){
        userPreferenceRepository.setLoggedIn(isLoggedIn)
    }

}