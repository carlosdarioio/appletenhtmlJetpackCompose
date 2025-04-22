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
    var lastName by remember { mutableStateOf<String?>(null) }
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
                    lastName = user.lastName
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
        if (name == null || lastName == null || name == "" || lastName == "") {
            CircularProgressIndicator()
        } else {
            Text(
                text = "¡Bienvenido $name $lastName!",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
