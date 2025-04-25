package com.example.appletenhtml.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.Alignment


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appletenhtml.models.Category
import com.example.appletenhtml.viewmodels.CategoryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCategoryScreen(
    navController: NavController,
    categoryId: Int,
    token:String,
    categoryViewModel: CategoryViewModel
) {
    val scope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var isLoaded by remember { mutableStateOf(false) }

    // Cargar los datos de la categoría solo una vez
    LaunchedEffect(key1 = categoryId) {
        val category = categoryViewModel.getCategoryById(categoryId)
        category?.let {
            nombre = it.nombre
            descripcion = it.descripcion
            isLoaded = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Categoría") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Mostrar propósito de esta vista
                        // (puedes abrir un dialog o navegar a una pantalla de ayuda)
                    }) {
                        Icon(Icons.Default.Info, contentDescription = "Ayuda")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = "https://cdfn3.com/storage/image-1.png",
                contentDescription = "Imagen decorativa",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            if (isLoaded) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        scope.launch {
                            val category = Category(
                                id = categoryId,
                                nombre = nombre,
                                descripcion = descripcion,
                                status = 1, // Puedes mantener el status si no se modifica aquí
                                createdAt = "", // O mantener el original si lo tienes
                                updatedAt = ""  // Igual con este
                            )

                            categoryViewModel.updateCategory(token, category)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Actualizar")
                }
            } else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}


