package com.example.appletenhtml.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.appletenhtml.models.Category

@Composable
fun CategoryListScreen(navController: NavController, categories: List<Category>) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Lista de categorías
        categories.forEach { category ->
            Text(text = category.nombre)
            Button(onClick = {
                navController.navigate("category_edit/${category.id}")
            }) {
                Text(text = "Editar")
            }
        }

        // Botón de crear
        Button(onClick = {
            navController.navigate("category_create")
        }) {
            Text(text = "Crear categoría")
        }
    }
}
