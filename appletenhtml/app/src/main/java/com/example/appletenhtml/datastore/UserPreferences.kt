package com.example.appletenhtml.datastore

import android.R
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
        val ID = stringPreferencesKey("id")
        val EMAIL = stringPreferencesKey("email")
        val TOKEN = stringPreferencesKey("token")
    }

    suspend fun saveUser(name: String, id: Int, email: String, token: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME] = name
            prefs[ID] = id.toString()
            prefs[EMAIL] = email
            prefs[TOKEN] = token
        }
    }

    fun getUser(): Flow<UserData> {
        return context.dataStore.data.map { prefs ->
            UserData(
                name = prefs[NAME] ?: "",
                id = prefs[ID] ?: "",
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
    val id: String,
    val email: String,
    val token: String
)
