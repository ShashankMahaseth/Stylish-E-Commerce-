package com.example.stylishe_commerceapp.presentation.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.domain.usecase.ForgotUseCase
import com.example.stylishe_commerceapp.domain.usecase.LoginUseCase
import com.example.stylishe_commerceapp.domain.usecase.LogoutUseCase
import com.example.stylishe_commerceapp.domain.usecase.SetUserPreferenceUseCase
import com.example.stylishe_commerceapp.domain.usecase.SignInWithGoogleUseCase
import com.example.stylishe_commerceapp.domain.usecase.SignUpUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val setUserPreferencesUseCase: SetUserPreferenceUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val forgotUseCase: ForgotUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<Result<String>>(Result.Idle)
    val authState = _authState.asStateFlow()
    fun login(email: String, password: String) {
        _authState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginUseCase(email, password)
            _authState.value = result
            if (result is Result.Success) {
                setUserPreferencesUseCase.setFirstTimeLogin(false)
                setUserPreferencesUseCase.setLoggedIn(true)
            }
        }
    }

    fun signUp(email: String, password: String) {
        _authState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = signUpUseCase(email, password)
            _authState.value = result
            if (result is Result.Success) {
                setUserPreferencesUseCase.setLoggedIn(false)
                setUserPreferencesUseCase.setFirstTimeLogin(false)
            }
        }
    }

    fun resetState() {
        _authState.value = Result.Idle
    }

    fun signInWithGoogle(account: GoogleSignInAccount) {
        _authState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = signInWithGoogleUseCase(account)
            _authState.value = result

            if (result is Result.Success) {
                setUserPreferencesUseCase.setFirstTimeLogin(false)
                setUserPreferencesUseCase.setLoggedIn(true)
            }

        }

    }

    fun forgot(email: String) {
        _authState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = forgotUseCase(email)
            _authState.value = result
        }

    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase()
            setUserPreferencesUseCase.setLoggedIn(false)
            setUserPreferencesUseCase.setFirstTimeLogin(true)
        }
    }
}