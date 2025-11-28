package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor (val authRepository: AuthRepository) {
    operator fun invoke() {
        authRepository.logout()
    }
}