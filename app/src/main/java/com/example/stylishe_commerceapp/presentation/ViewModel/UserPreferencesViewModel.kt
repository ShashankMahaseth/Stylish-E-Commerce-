package com.example.stylishe_commerceapp.presentation.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.domain.usecase.SetUserPreferenceUseCase
import com.example.stylishe_commerceapp.data.model.UserPreferenceState
import com.example.stylishe_commerceapp.domain.usecase.GetUserPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val getUserPreferencesUseCase: GetUserPreferenceUseCase,
    private val setUserPreferencesUseCase: SetUserPreferenceUseCase
): ViewModel() {
    private val _state = MutableStateFlow(UserPreferenceState())
    val state = _state.asStateFlow()
    init {
        observeUserPreferences()
    }

    private fun observeUserPreferences(){
        viewModelScope.launch {
            combine(
                getUserPreferencesUseCase.isFirstTimeLogin(),//false
                getUserPreferencesUseCase.isLoggedIn()//true
            ){isFirstTime,isLoggedIn->
                UserPreferenceState(
                    isFirstTimeLogin = isFirstTime,
                    isLoggedIn=isLoggedIn,
                    isLoading = true
                )
            }.collect {newState->
                _state.value =newState

            }
        }
    }
}