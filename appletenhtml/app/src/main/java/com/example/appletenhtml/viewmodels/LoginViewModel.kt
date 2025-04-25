package com.example.appletenhtml.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appletenhtml.datastore.DataStoreManager
import com.example.appletenhtml.models.LoginRequest
import com.example.appletenhtml.models.User
import com.example.appletenhtml.network.LoginApi
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
class LoginViewModel(
    private val loginApi: LoginApi,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    var loginState = mutableStateOf<LoginResult?>(null)
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val loginRequest = LoginRequest(email = email, password = password)
                val response = loginApi.login(loginRequest)

                val user = response.body()
                if (user != null) {
                    dataStoreManager.saveUser(
                        id = user.id,
                        name = user.name,
                        email = user.email,
                        token = user.token
                    )
                    loginState.value = LoginResult.Success(user)
                } else {
                    loginState.value = LoginResult.Error("Respuesta del servidor vacía.")
                }

            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val message = try {
                    JSONObject(errorBody ?: "").getString("message")
                } catch (e: Exception) {
                    "Error desconocido"
                }
                loginState.value = LoginResult.Error(message)
            } catch (e: Exception) {
                loginState.value = LoginResult.Error("Error de red: ${e.message}")
            } finally {
                isLoading.value = false
            }
        }
    }
}
