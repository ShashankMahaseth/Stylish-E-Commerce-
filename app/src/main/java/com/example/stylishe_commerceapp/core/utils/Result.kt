package com.example.stylishe_commerceapp.core.utils


sealed class Result<out T> {
    data object Idle : Result<Nothing>()
    data object Loading: Result<Nothing>()
    data class Success<T>(val data:T) : Result<T>()
    data class Failure(val message: String) : Result<String>()
}