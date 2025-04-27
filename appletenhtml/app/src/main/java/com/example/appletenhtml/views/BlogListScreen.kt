package com.example.appletenhtml.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.appletenhtml.viewmodels.BlogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListScreen(navController: NavController, blogViewModel: BlogViewModel) {
    val blogs by blogViewModel.blogs
    val isLoading by blogViewModel.isLoading
    val errorMessage by blogViewModel.errorMessage

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Llamar a getBlogs() cuando la pantalla se compone
    LaunchedEffect(Unit) {
        blogViewModel.getBlogs()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Blogs") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                blogs.isNotEmpty() -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(blogs) { blog ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = blog.title,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "Autor: ${blog.author.displayName}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
                else -> {
                    Text(
                        text = "No hay blogs disponibles.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

}

@Composable
fun BlogItem(title: String, authorName: String, authorImage: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (authorImage.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(authorImage),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = authorName, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
