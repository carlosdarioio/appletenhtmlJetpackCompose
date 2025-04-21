package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EjemploBisiesto() {
    var yearInput by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Verificar Año Bisiesto", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = yearInput,
            onValueChange = {
                yearInput = it
                error = null
            },
            label = { Text("Ingresa un año") }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                val year = yearInput.toIntOrNull()

                if (year == null || year < 0) {
                    error = "Por favor ingresa un año válido"
                    resultado = null
                } else {
                    val isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
                    resultado = if (isLeap) "El año $year es bisiesto" else "El año $year no es bisiesto"
                    error = null
                }
            }) {
                Text("Verificar")
            }

            Button(onClick = {
                yearInput = ""
                resultado = null
                error = null
            }) {
                Text("Limpiar")
            }
        }

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        resultado?.let {
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
