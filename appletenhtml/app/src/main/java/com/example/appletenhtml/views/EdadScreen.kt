package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun EjemploEdad() {
    var yearInput by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Calcular Edad", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = yearInput,
            onValueChange = {
                yearInput = it
                error = null
            },
            label = { Text("Año de nacimiento") }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                val birthYear = yearInput.toIntOrNull()

                if (birthYear == null || birthYear > currentYear || birthYear < 1900) {
                    error = "Por favor ingresa un año válido"
                    edad = null
                } else {
                    val calculada = currentYear - birthYear
                    edad = "Tienes $calculada años"
                    error = null
                }
            }) {
                Text("Calcular")
            }

            Button(onClick = {
                yearInput = ""
                edad = null
                error = null
            }) {
                Text("Limpiar")
            }
        }

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        edad?.let {
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
