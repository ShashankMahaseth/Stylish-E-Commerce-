package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository){
    suspend operator fun invoke(email:String,password: String): Result<String> {
        return  repository.login(email,password)
    }
}