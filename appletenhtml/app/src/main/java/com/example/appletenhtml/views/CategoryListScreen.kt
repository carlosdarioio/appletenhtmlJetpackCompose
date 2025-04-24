package com.example.appletenhtml.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.appletenhtml.models.Category
import com.example.appletenhtml.viewmodels.CategoryViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

//esta en pinares y no le conecta
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel
) {
    val categoryList by categoryViewModel.categories.collectAsState()
    val context = LocalContext.current

    LaunchedEffect (Unit) {
        categoryViewModel.getAllCategories()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Lista de Categorías") },
                navigationIcon = {
                    IconButton (onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Aquí podrías mostrar un dialog explicando el propósito de esta vista
                        Toast.makeText(context, "Aquí se muestran todas las categorías disponibles", Toast.LENGTH_LONG).show()
                    }) {
                        Icon(Icons.Default.Info, contentDescription = "Ayuda")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("create_category") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Categoría")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)) {

            Image(
                painter = rememberAsyncImagePainter("https://cdfn3.com/storage/image-1.png"),
                contentDescription = "Imagen decorativa",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(categoryList) { category ->
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("ID: ${category.id}", style = MaterialTheme.typography.labelSmall)
                            Text("Nombre: ${category.nombre}", style = MaterialTheme.typography.titleMedium)
                            Text("Descripción: ${category.descripcion}", style = MaterialTheme.typography.bodySmall)
                            Text("Estado: ${if (category.status == 1) "Activo" else "Inactivo"}", style = MaterialTheme.typography.bodySmall)

                            Spacer(modifier = Modifier.height(8.dp))

                            Row {
                                Button(
                                    onClick = { navController.navigate("show_category/${category.id}") },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text("Ver")
                                }
                                Button(
                                    onClick = { navController.navigate("edit_category/${category.id}") },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text("Editar")
                                }
                                Button(
                                    onClick = {
                                        categoryViewModel.deleteCategory(category.id)
                                        Toast.makeText(context, "Categoría desactivada", Toast.LENGTH_SHORT).show()
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.error
                                    )
                                ) {
                                    Text("Borrar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
