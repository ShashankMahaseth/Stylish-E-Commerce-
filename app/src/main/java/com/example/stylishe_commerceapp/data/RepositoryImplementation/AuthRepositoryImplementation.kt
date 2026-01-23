package com.example.stylishe_commerceapp.data.RepositoryImplementation

import android.content.Context
import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.example.stylishe_commerceapp.core.utils.Result
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class  AuthRepositoryImplementation @Inject constructor(
    val firebaseAuth: FirebaseAuth,
     val context: Context
): AuthRepository {
    override suspend fun login(email: String, password: String) : Result<String> {
        return try{
            firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Result.Success("Login Successful")
        }catch (e: Exception){
            Result.Failure("Failure")

        }
    }

    override suspend fun signup(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email,password).await()

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

    override suspend fun forgot(email: String): Result<String> {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            return Result.Success("Email Sent")
        } catch (e: Exception) {
            return Result.Failure(e.localizedMessage ?: "Unknown Error")
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
        val googleClient = GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        )
        googleClient.signOut()
    }
}