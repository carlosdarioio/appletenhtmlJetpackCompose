package com.example.appletenhtml.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

//private val Context. by preferencesDataStore("user_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val ID_KEY = stringPreferencesKey("id")
    }

    suspend fun saveUser(id: Int, name: String, email: String, token: String) {
        context.dataStore.edit { preferences ->
            preferences[ID_KEY] = id.toString()
            preferences[NAME_KEY] = name
            preferences[EMAIL_KEY] = email
            preferences[TOKEN_KEY] = token
        }
    }

    fun getUserToken(): Flow<String?> {
        return context.dataStore.data.map { it[TOKEN_KEY] }
    }
    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { it[TOKEN_KEY] }
    }

    fun getUserName(): Flow<String?> {
        return context.dataStore.data.map { it[NAME_KEY] }
    }

    fun getUserEmail(): Flow<String?> {
        return context.dataStore.data.map { it[EMAIL_KEY] }
    }

    fun getUserId(): Flow<String?> {
        return context.dataStore.data.map { it[ID_KEY] }
    }

    suspend fun clearUserData() {
        context.dataStore.edit { it.clear() }
    }
}
