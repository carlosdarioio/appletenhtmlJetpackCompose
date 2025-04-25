package com.example.appletenhtml.views


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.appletenhtml.viewmodels.CategoryViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.appletenhtml.datastore.DataStoreManager
import com.example.appletenhtml.models.Category
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    categoryId: Int,
    navController: NavController,
    categoryViewModel: CategoryViewModel
) {
    val category = remember { mutableStateOf<Category?>(null) }

    LaunchedEffect(categoryId) {
        category.value = categoryViewModel.getCategoryById(categoryId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Categoría") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Aquí podrías abrir un diálogo explicando la vista
                    }) {
                        Icon(Icons.Default.Info, contentDescription = "Ayuda")
                    }
                }
            )
        }
    ) { padding ->
        if (category.value != null) {
            val cat = category.value!!
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://cdfn3.com/storage/image-1.png"),
                    contentDescription = "Decoración",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Nombre: ${cat.nombre}", style = MaterialTheme.typography.titleLarge)
                Text("Descripción: ${cat.descripcion}", style = MaterialTheme.typography.bodyLarge)
                Text("Estado: ${if (cat.status == 1) "Activo" else "Inactivo"}", style = MaterialTheme.typography.bodyMedium)
                Text("Creado: ${cat.createdAt}", style = MaterialTheme.typography.bodySmall)
                Text("Actualizado: ${cat.updatedAt}", style = MaterialTheme.typography.bodySmall)
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

