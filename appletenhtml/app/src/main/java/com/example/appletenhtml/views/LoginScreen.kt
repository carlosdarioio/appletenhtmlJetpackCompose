package com.example.appletenhtml.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.appletenhtml.datastore.UserPreferences
import com.example.appletenhtml.models.User
import com.example.appletenhtml.ui.VistaConEstilo
import com.example.appletenhtml.viewmodels.LoginResult
import com.example.appletenhtml.viewmodels.LoginViewModel
import com.example.appletenhtml.viewmodels.ThemeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel,themeviewModel: ThemeViewModel) {
    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val loginState = loginViewModel.loginState.value
    val isLoading = loginViewModel.isLoading.value


    Scaffold(
        Modifier.padding(start = 16.dp),
        topBar = {
            TopAppBar(
                title = { Text("Login") },
                actions = {
                    IconButton(onClick = { themeviewModel.toggleTheme() }) {
                        Icon(
                            imageVector = if (themeviewModel.isDarkTheme.value) Icons.Default.Add else Icons.Default.Delete,
                            contentDescription = "Toggle Theme"
                        )
                    }
                }
            )
        }
    ) {padding ->Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginViewModel.login(email, password)
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }

        when (loginState) {
            is LoginResult.Error -> {
                Text(loginState.message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
            }
            is LoginResult.Success -> {
                // Navegamos si aún no navegamos (controlá que no lo haga múltiples veces)
                LaunchedEffect(Unit) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
            null -> {}
        }
    }

    }


}
