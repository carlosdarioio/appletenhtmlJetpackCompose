package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.appletenhtml.datastore.UserPreferences
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    var name by remember { mutableStateOf<String?>(null) }
    var email by remember { mutableStateOf<String?>(null) }
    var token by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            userPreferences.getUser().collectLatest { user ->
                token = user.token
                if (user.token.isBlank()) {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                } else {
                    name = user.name
                    email = user.email
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (name == null || email == null || name == "" || email == "") {
            CircularProgressIndicator()
        } else {
            Text(
                text = "¡Bienvenido $name ( $email )!",
                style = MaterialTheme.typography.headlineMedium
            )
            Button(
                onClick = {
                    scope.launch {
                        userPreferences.clear()
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text("Cerrar sesión")
            }
            val examples = listOf(
                "1. Ejemplo de suma" to "suma",
                "2. Conversión Int/String/Float" to "conversion",
                "3. Calcular edad" to "edad",
                "4. Año bisiesto" to "bisiesto",
                "5. Ciclo for" to "ciclo_for",
                "6. Par o impar" to "par_impar",
                "7. Login auth" to "login",
                "8. CRUD con API" to "crud"
            )

            Spacer(modifier = Modifier.height(24.dp))

            examples.forEach { (title, route) ->
                Button(
                    onClick = { navController.navigate(route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(title)
                }
            }


        }
    }
}
