package com.example.stylishe_commerceapp.domain.repository

import com.example.stylishe_commerceapp.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import com.example.stylishe_commerceapp.core.utils.Result

interface UserSettingRepository {
    suspend fun saveUserProfile(userProfile: UserProfile): Result<Unit>

    fun getUserProfile(userId: String): Flow<Result<UserProfile>>

    suspend fun updateUserProfile(userId: String, updates: Map<String, Any>): Result<Unit>
}

