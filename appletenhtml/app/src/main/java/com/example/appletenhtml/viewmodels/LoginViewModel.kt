package com.example.appletenhtml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appletenhtml.datastore.UserPreferences
import com.example.appletenhtml.models.LoginRequest
import com.example.appletenhtml.models.LoginResponse
import com.example.appletenhtml.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val response: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
class LoginViewModel(private val userPreferences: UserPreferences) : ViewModel() {

//class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                val request = LoginRequest(email, password)
                val response = RetrofitInstance.api.loginUser(request)

                if (response.isSuccessful && response.body() != null) {

                    val loginResponse = response.body()!!

                    // Guardar datos en DataStore
                    viewModelScope.launch {
                        userPreferences.saveUser(
                            name = loginResponse.name,
                            id = loginResponse.id,
                            email = loginResponse.email,
                            token = loginResponse.token
                        )
                    }

                    _uiState.value = LoginUiState.Success(loginResponse)

                } else {
                    //val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                    //_uiState.value = LoginUiState.Error(errorMessage)
                    val errorJson = response.errorBody()?.string()
                    val message = try {
                        JSONObject(errorJson ?: "").getString("message")
                    } catch (e: Exception) {
                        "xError desconocido"
                    }
                    _uiState.value = LoginUiState.Error(message)
                }

            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error("Error de red: ${e.message}")
            }
        }
    }

    fun resetState() {
        _uiState.value = LoginUiState.Idle
    }
}
