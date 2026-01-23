package com.example.stylishe_commerceapp.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.domain.model.UserProfile
import com.example.stylishe_commerceapp.domain.repository.UserSettingRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingState(
    val userProfile: UserProfile = UserProfile(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false,
    val profilePhotoUrl: String? = null

)

@HiltViewModel
class SettingVIewModel @Inject constructor(
    val userSettingRepository: UserSettingRepository,
    val firebaseAuth: FirebaseAuth,
) :
    ViewModel() {
    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    init {
        loadUserProfile()
        loadUserData()
    }

    fun loadUserData() {
        val currentUser = firebaseAuth.currentUser
        val email = currentUser?.email ?: ""
        val photoUrl = currentUser?.photoUrl?.toString()

        _state.value = _state.value.copy(
            userProfile = _state.value.userProfile.copy(email = email),
            profilePhotoUrl = photoUrl
        )

    }

    fun loadUserProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            val userId = firebaseAuth.currentUser?.uid ?: return@launch

            userSettingRepository.getUserProfile(userId)
                .collect { result ->//collect will receive updates whenever profile changes in Firebase
                    when (result) {
                        is Result.Success -> {
                            _state.value = _state.value.copy(
                                userProfile = result.data,
                                isLoading = false,
                                error = null

                            )
                        }

                        is Result.Failure -> {
                            _state.value = _state.value.copy(
                                error = result.message,
                                isLoading = false,

                                )
                        }

                        else -> {}
                    }
                }
        }
    }


    fun updateUserProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            try {
                val userId = firebaseAuth.currentUser?.uid
                if (userId == null) {
                    _state.value = _state.value.copy(
                        isSaving = false,
                        saveSuccess = false,
                        error = "User not logged in"
                    )
                    return@launch//helps to exit early
                }
                _state.value = _state.value.copy(isSaving = true, saveSuccess = false, error = null)

                val profileWithUserId = userProfile.copy(userId = userId)
                when (val result =
                    userSettingRepository.saveUserProfile(userProfile = profileWithUserId)) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            userProfile = userProfile,
                            isSaving = false,
                            saveSuccess = true,
                            error = null,
                            isLoading = false
                        )

                    }

                    is Result.Failure -> {
                        _state.value = _state.value.copy(
                            isSaving = false,
                            saveSuccess = false,
                            error = result.message,
                            isLoading = false
                        )
                    }

                    else -> {
                        _state.value = _state.value.copy(
                            isSaving = false,
                            saveSuccess = false,
                            error = "Unknown Error",
                            isLoading = false

                        )

                    }
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isSaving = false,
                    saveSuccess = false,
                    error = e.localizedMessage ?: "Unknown Error",
                    isLoading = false
                )
            }

        }
    }

    fun resetSaveSuccess() {
        _state.value = _state.value.copy(saveSuccess = false)
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }


}







