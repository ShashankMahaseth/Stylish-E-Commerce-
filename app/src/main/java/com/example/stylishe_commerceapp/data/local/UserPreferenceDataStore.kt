package com.example.stylishe_commerceapp.data.local


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesDataStore(private val context: Context) {
    //To learn
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences")//Creating folder/file in internal Storage of our device to Store key and value
        private val IS_FIRST_TIME_LOGIN = booleanPreferencesKey("is_first_time_login")//key
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")//key
    }
    val isFirstTimeLogin: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_FIRST_TIME_LOGIN] ?: true//if null then do by default true
    }
    val isLoggedIn : Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN]?:false//if null then do by default false

    }
    suspend fun setFirstTimeLogin(isFirstTime: Boolean){//access in viewModel in login fun
        context.dataStore.edit { preferences ->//updating
            preferences[IS_FIRST_TIME_LOGIN] =isFirstTime
        }
    }
    suspend fun setLoginIn(isLoggedIn: Boolean){//access in viewmodel in login fun
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] =isLoggedIn

        }


    }





}