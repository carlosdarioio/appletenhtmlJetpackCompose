package com.appletenhtml.views

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
fun ParImparScreen(navController: NavHostController) {
    var numero by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }

    VistaConEstilo (
        titulo = "Número Par o Impar",
        descripcionAyuda = "Determina si el número ingresado es par o impar.",
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
                if (n % 2 == 0) "$n es par" else "$n es impar"
            } else {
                "Número inválido"
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Verificar")
        }

        if (resultado.isNotBlank()) {
            Text(resultado, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))
        }
    }
}