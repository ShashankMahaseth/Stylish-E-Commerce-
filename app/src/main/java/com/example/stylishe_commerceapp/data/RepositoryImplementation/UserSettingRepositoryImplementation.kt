package com.example.stylishe_commerceapp.data.RepositoryImplementation

import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.domain.model.UserProfile
import com.example.stylishe_commerceapp.domain.repository.UserSettingRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserSettingRepositoryImplementation @Inject constructor(private val database: FirebaseDatabase) :
    UserSettingRepository {
    val userRef = database.getReference("users")

    override suspend fun saveUserProfile(userProfile: UserProfile): Result<Unit> {
        return try {
            if (userProfile.userId.isEmpty()) {
                return Result.Failure("User ID is empty")
            }
           // A HashMap is used because it allows you to store and access data very fast using a key–value pair.
            val profileMap = hashMapOf<String, Any>(
                "userid" to userProfile.userId,
                "name" to userProfile.name,
                "email" to userProfile.email,
                "pinCode" to userProfile.pinCode,
                "address" to userProfile.address,
                "city" to userProfile.city,
                "state" to userProfile.state,
                "country" to userProfile.country,
                "upiId" to userProfile.upiId
            )
            userRef.child(userProfile.userId).setValue(profileMap).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(e.localizedMessage ?: "Unknown Error")
        }
    }

    override fun getUserProfile(userId: String): Flow<Result<UserProfile>> = callbackFlow {//callbackFlow converts callback-based APIs into a Kotlin Flow.
        val listener = object : ValueEventListener {//A listener is a piece of code that waits for an event and runs automatically when that event occurs.
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val userProfile = snapshot.getValue(UserProfile::class.java)//snapShort helps to contains all the data from the firebase database
                    if (userProfile != null) {
                      trySend(Result.Success(userProfile))
                    } else {
                        trySend(Result.Success(UserProfile(userId=userId)))
                    }
                } catch (e: Exception) {
                   trySend( Result.Failure(e.localizedMessage ?: "Unknown Error"))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Result.Failure(error.message))//if data comes/send then run otherwise no
            }
        }
        userRef.child(userId).addValueEventListener(listener)
        awaitClose {
            userRef.child(userId)
        }
    }

    override suspend fun updateUserProfile(userId: String,updates: Map<String, Any>): Result<Unit> {
        return try {
            userRef.child(userId).updateChildren(updates).await()
            Result.Success(Unit)
        }catch (e: Exception){
            Result.Failure(e.localizedMessage ?: "Failed to update profile")
        }

    }
}
