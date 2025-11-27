package com.example.stylishe_commerceapp.data.RepositoryImplementation

import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.example.stylishe_commerceapp.core.utils.Result
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
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

    override suspend fun googleLogin(account: GoogleSignInAccount): Result<String> {
        return try {
            val credential = GoogleAuthProvider.getCredential(account.idToken,null)

            val authResult = firebaseAuth.signInWithCredential(credential).await()
            Result.Success("Google SignIn Success")

        }catch (e: Exception){

            Result.Failure(e.localizedMessage ?:"Unknown Error")

        }
    }


}