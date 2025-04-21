package com.example.appletenhtml.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EjemploConversion() {
    var input by remember { mutableStateOf("") }
    var intResult by remember { mutableStateOf<String?>(null) }
    var floatResult by remember { mutableStateOf<String?>(null) }
    var stringFromInt by remember { mutableStateOf<String?>(null) }
    var stringFromFloat by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Conversión entre Int, String y Float", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
                error = null
            },
            label = { Text("Ingresa un valor (String)") }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                try {
                    val intValue = input.toInt()
                    val floatValue = input.toFloat()

                    intResult = intValue.toString()
                    floatResult = floatValue.toString()
                    stringFromInt = intValue.toString()
                    stringFromFloat = floatValue.toString()
                    error = null
                } catch (e: NumberFormatException) {
                    intResult = null
                    floatResult = null
                    stringFromInt = null
                    stringFromFloat = null
                    error = "Entrada inválida. No se puede convertir a número."
                }
            }) {
                Text("Convertir")
            }

            Button(onClick = {
                input = ""
                intResult = null
                floatResult = null
                stringFromInt = null
                stringFromFloat = null
                error = null
            }) {
                Text("Limpiar")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        intResult?.let {
            Text("Int: $it")
        }

        floatResult?.let {
            Text("Float: $it")
        }

        stringFromInt?.let {
            Text("String desde Int: $it")
        }

        stringFromFloat?.let {
            Text("String desde Float: $it")
        }
    }
}
