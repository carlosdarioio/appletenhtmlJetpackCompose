package com.example.appletenhtml.views

import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appletenhtml.ui.VistaConEstilo

@Composable
fun SumaScreen(navController: NavHostController) {
    VistaConEstilo (
        navController = navController,
        titulo = "Ejemplo de Suma",
        descripcionAyuda = "Esta vista permite sumar dos números y mostrar el resultado."
    ) {
        var numero1 by rememberSaveable { mutableStateOf("") }
        var numero2 by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Número 1") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Número 2") },
            modifier = Modifier.fillMaxWidth()
        )

        val resultado = numero1.toIntOrNull()?.plus(numero2.toIntOrNull() ?: 0) ?: 0

        Text(
            text = "Resultado: $resultado",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}