package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appletenhtml.datastore.UserPreferences
import com.example.appletenhtml.models.User
import com.example.appletenhtml.viewmodels.LoginUiState
import com.example.appletenhtml.viewmodels.LoginViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    val viewModel: LoginViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(userPreferences) as T
            }
        })

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        when (uiState) {
            is LoginUiState.Success -> {
                isLoading = false
                val user = (uiState as LoginUiState.Success).response.user
                navController.navigate("home"){popUpTo("login"){inclusive=true} }
                viewModel.resetState()
            }
            is LoginUiState.Error -> {
                isLoading = false
                error = (uiState as LoginUiState.Error).message
            }
            is LoginUiState.Loading -> {
                isLoading = true
                error = null
            }
            else -> {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Login", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    error = "Por favor completa todos los campos"
                } else {
                    viewModel.login(email, password)
                }
            },
            enabled = !isLoading
        ) {
            Text("Iniciar sesión")
        }

        if (isLoading) {
            CircularProgressIndicator()
        }

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
