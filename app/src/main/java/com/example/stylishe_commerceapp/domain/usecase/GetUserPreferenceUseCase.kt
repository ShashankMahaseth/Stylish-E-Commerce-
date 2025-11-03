package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.domain.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserPreferenceUseCase @Inject constructor( private val userPreferenceRepository: UserPreferenceRepository) {
    fun isFirstTimeLogin(): Flow<Boolean> =userPreferenceRepository.isFirstTimeLogin
    fun isLoggedIn(): Flow<Boolean> =userPreferenceRepository.isLoggedIn// flow use to store updated data
}