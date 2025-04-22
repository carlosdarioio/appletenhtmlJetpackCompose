package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appletenhtml.ui.VistaConEstilo

@Composable
fun BisiestoScreen(navController: NavHostController) {
    var anio by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }

    VistaConEstilo (
        titulo = "Año Bisiesto",
        descripcionAyuda = "Verifica si un año ingresado es bisiesto.",
        navController = navController
    ) {
        OutlinedTextField(
            value = anio,
            onValueChange = { anio = it },
            label = { Text("Año") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(onClick = {
            val year = anio.toIntOrNull()
            resultado = if (year != null) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    "$year es bisiesto"
                } else {
                    "$year no es bisiesto"
                }
            } else {
                "Ingresa un año válido"
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Verificar")
        }

        if (resultado.isNotBlank()) {
            Text(resultado, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))
        }
    }
}