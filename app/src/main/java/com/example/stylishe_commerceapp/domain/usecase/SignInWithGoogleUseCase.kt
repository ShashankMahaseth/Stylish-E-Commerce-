package com.example.stylishe_commerceapp.domain.usecase

import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(val repository: AuthRepository) {
    suspend operator fun invoke(account: GoogleSignInAccount): Result<String>{
        return repository.googleLogin(account)
    }
}