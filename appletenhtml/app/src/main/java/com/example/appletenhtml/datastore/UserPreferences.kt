package com.example.appletenhtml.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFERENCE_NAME = "user_prefs"

val Context.dataStore by preferencesDataStore(name = PREFERENCE_NAME)

class UserPreferences(private val context: Context) {

    companion object {
        val NAME = stringPreferencesKey("name")
        val LAST_NAME = stringPreferencesKey("last_name")
        val EMAIL = stringPreferencesKey("email")
        val TOKEN = stringPreferencesKey("token")
    }

    suspend fun saveUser(name: String, lastName: String, email: String, token: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME] = name
            prefs[LAST_NAME] = lastName
            prefs[EMAIL] = email
            prefs[TOKEN] = token
        }
    }

    fun getUser(): Flow<UserData> {
        return context.dataStore.data.map { prefs ->
            UserData(
                name = prefs[NAME] ?: "",
                lastName = prefs[LAST_NAME] ?: "",
                email = prefs[EMAIL] ?: "",
                token = prefs[TOKEN] ?: ""
            )
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}

data class UserData(
    val name: String,
    val lastName: String,
    val email: String,
    val token: String
)
