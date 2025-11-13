package com.example.stylishe_commerceapp.core.di




import android.content.Context
import com.example.stylishe_commerceapp.data.RepositoryImplementation.AuthRepositoryImplementation
import com.example.stylishe_commerceapp.data.RepositoryImplementation.ProductRepositoryImplementation
import com.example.stylishe_commerceapp.data.RepositoryImplementation.UserPreferenceImplementation
import com.example.stylishe_commerceapp.data.local.UserPreferencesDataStore
import com.example.stylishe_commerceapp.data.service.ProductApiService
import com.example.stylishe_commerceapp.domain.repository.AuthRepository
import com.example.stylishe_commerceapp.domain.repository.ProductRepository
import com.example.stylishe_commerceapp.domain.repository.UserPreferenceRepository
import com.google.firebase.auth.FirebaseAuth

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserPreferenceDataStore(@ApplicationContext context: Context): UserPreferencesDataStore {
        return UserPreferencesDataStore(context)
    }


    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {


        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
            defaultRequest {
                url {
                    protocol= URLProtocol.HTTPS
                    host= "dummyjson.com"
                }

            }
            install(Logging){//This plugin helps you see what’s happening in network requests and responses — very useful for debugging API calls.
                level = LogLevel.BODY //Includes full request and response bodies
            }
        }

    }
   @Provides
    @Singleton
    fun provideProductApiService(httpClient: HttpClient): ProductApiService {
        return ProductApiService(httpClient)
    }
    @Provides
    @Singleton
    fun provideProductRepository(productApiService: ProductApiService): ProductRepository {
        return ProductRepositoryImplementation(productApiService)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImplementation(firebaseAuth)
    }
    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(userPreferencesDataStore: UserPreferencesDataStore): UserPreferenceRepository {
        return UserPreferenceImplementation(userPreferencesDataStore)
    }
}
