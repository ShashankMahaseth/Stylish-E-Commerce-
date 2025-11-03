package com.example.stylishe_commerceapp.domain.repository

import com.example.stylishe_commerceapp.core.utils.Result

interface  AuthRepository {
    suspend fun login(email:String,password: String) : Result<String>
    suspend fun signup(email:String,password: String) : Result<String>
    suspend fun googleLogin(email: String,password: String) : Result<String>
}