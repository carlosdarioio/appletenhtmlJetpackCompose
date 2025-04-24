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
import androidx.navigation.NavHostController
import com.example.appletenhtml.datastore.UserPreferences
import com.example.appletenhtml.ui.VistaConEstilo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    //userPreferences: UserPreferences
) {
    val context = LocalContext.current
    var userPreferences=UserPreferences(context);
    val userData = userPreferences.getUser().collectAsState(initial = null)

    VistaConEstilo(
        titulo = "Pantalla de Inicio",
        descripcionAyuda = "Pantalla principal después de iniciar sesión.",
        navController = navController
    ) {
        userData.value?.let { user ->
            Text("¡Bienvenido ${user.name}!", style = MaterialTheme.typography.headlineSmall)
        } ?: Text("¡Bienvenido!", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("category_list")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Categorías")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    userPreferences.clear()
                }
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}