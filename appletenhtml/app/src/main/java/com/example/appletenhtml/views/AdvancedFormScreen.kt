// AdvancedFormScreen.kt
package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedFormScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var ageError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

        topBar = {
            TopAppBar(
                title = { Text("Formulario Avanzado") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError = it.isBlank()
                },
                label = { Text("Nombre") },
                isError = nameError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (nameError) Text("El nombre es obligatorio", color = MaterialTheme.colorScheme.error)

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                },
                label = { Text("Correo electrónico") },
                isError = emailError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            if (emailError) Text("Correo inválido", color = MaterialTheme.colorScheme.error)

            OutlinedTextField(
                value = age,
                onValueChange = {
                    age = it
                    ageError = it.toIntOrNull() == null
                },
                label = { Text("Edad") },
                isError = ageError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (ageError) Text("Edad debe ser un número válido", color = MaterialTheme.colorScheme.error)

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.length < 6
                },
                label = { Text("Contraseña") },
                isError = passwordError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            if (passwordError) Text("La contraseña debe tener al menos 6 caracteres", color = MaterialTheme.colorScheme.error)

            Button(
                onClick = {
                    nameError = name.isBlank()
                    emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    ageError = age.toIntOrNull() == null
                    passwordError = password.length < 6

                    if (!nameError && !emailError && !ageError && !passwordError) {
                        // Aquí podrías hacer un submit real más adelante
                        println("Formulario válido")
                       
                    }








                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Validar")
            }
        }
    }
}
