package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EjemploCicloFor() {
    var numeroInput by remember { mutableStateOf("") }
    var tabla by remember { mutableStateOf<List<String>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Tabla de multiplicar con ciclo for", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = numeroInput,
            onValueChange = {
                numeroInput = it
                error = null
            },
            label = { Text("Ingresa un número") }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                val numero = numeroInput.toIntOrNull()
                if (numero == null) {
                    error = "Entrada inválida. Ingresa un número entero."
                    tabla = emptyList()
                } else {
                    val resultados = mutableListOf<String>()
                    for (i in 1..10) {
                        resultados.add("$numero x $i = ${numero * i}")
                    }
                    tabla = resultados
                    error = null
                }
            }) {
                Text("Mostrar tabla")
            }

            Button(onClick = {
                numeroInput = ""
                tabla = emptyList()
                error = null
            }) {
                Text("Limpiar")
            }
        }

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        tabla.forEach { resultado ->
            Text(text = resultado)
        }
    }
}
