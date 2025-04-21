package com.example.appletenhtml.views

import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun EjemploSuma() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Ejemplo de Suma", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = numero1,
            onValueChange = {
                numero1 = it
                error = null
            },
            label = { Text("Número 1") },
            isError = error != null && numero1.isEmpty()
        )

        OutlinedTextField(
            value = numero2,
            onValueChange = {
                numero2 = it
                error = null
            },
            label = { Text("Número 2") },
            isError = error != null && numero2.isEmpty()
        )

        Button(onClick = {
            val num1 = numero1.toFloatOrNull()
            val num2 = numero2.toFloatOrNull()

            if (num1 == null || num2 == null) {
                error = "Por favor ingresa valores numéricos válidos"
                resultado = null
            } else {
                resultado = (num1 + num2).toString()
                error = null
            }
        }) {
            Text("Sumar")
        }

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        resultado?.let {
            Text(
                text = "Resultado: $it",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}