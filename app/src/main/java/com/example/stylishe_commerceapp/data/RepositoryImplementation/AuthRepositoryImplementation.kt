package com.example.stylishe_commerceapp.data.RepositoryImplementation

import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.example.stylishe_commerceapp.core.utils.Result
import javax.inject.Inject

class  AuthRepositoryImplementation @Inject constructor(
    val firebaseAuth: FirebaseAuth
): AuthRepository {
    override suspend fun login(email: String, password: String) : Result<String> {
        return try{
            firebaseAuth.signInWithEmailAndPassword(email,password)
            Result.Success("Login Successful")
        }catch (e: Exception){
            Result.Failure("Failure")

        }
    }

    override suspend fun signup(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email,password)
            Result.Success("Successful")
        }catch (e: Exception){
            Result.Failure(e.localizedMessage ?:"Unknown Error")
        }
    }

    override suspend fun googleLogin(email: String, password: String): Result<String> {
        TODO("Not yet implemented")
    }


}