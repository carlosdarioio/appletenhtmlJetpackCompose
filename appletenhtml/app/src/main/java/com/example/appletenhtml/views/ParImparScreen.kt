package com.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EjemploParImpar() {
    var numeroInput by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf<String?>(null) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Número Par o Impar", style = MaterialTheme.typography.headlineSmall)

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
                    resultado = null
                } else {
                    resultado = if (numero % 2 == 0) {
                        "El número $numero es PAR"
                    } else {
                        "El número $numero es IMPAR"
                    }
                    error = null
                }
            }) {
                Text("Verificar")
            }

            Button(onClick = {
                numeroInput = ""
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
