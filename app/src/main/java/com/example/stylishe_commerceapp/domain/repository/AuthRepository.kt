package com.example.stylishe_commerceapp.domain.repository

import com.example.stylishe_commerceapp.core.utils.Result
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<String>
    suspend fun signup(email: String, password: String): Result<String>
    suspend fun googleLogin(account: GoogleSignInAccount): Result<String>

     fun logout()
}