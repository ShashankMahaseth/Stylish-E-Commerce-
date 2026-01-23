package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotUseCase @Inject constructor(val repository: AuthRepository) {
    suspend operator fun invoke(email: String) = repository.forgot(email)
}
