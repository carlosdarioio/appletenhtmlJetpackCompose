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
fun TablaForScreen(navController: NavHostController) {
    var numero by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }

    VistaConEstilo (
        titulo = "Tabla de Multiplicar",
        descripcionAyuda = "Muestra la tabla del número que ingreses usando un ciclo for.",
        navController = navController
    ) {
        OutlinedTextField(
            value = numero,
            onValueChange = { numero = it },
            label = { Text("Número") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(onClick = {
            val n = numero.toIntOrNull()
            resultado = if (n != null) {
                (1..10).joinToString("\n") { "$n x $it = ${n * it}" }
            } else {
                "Ingresa un número válido"
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Mostrar tabla")
        }

        if (resultado.isNotBlank()) {
            Text(resultado, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
