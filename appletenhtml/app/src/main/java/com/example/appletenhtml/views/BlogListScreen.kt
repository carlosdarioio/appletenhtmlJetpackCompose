package com.example.appletenhtml.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.compose.rememberAsyncImagePainter
import com.example.appletenhtml.viewmodels.BlogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListScreen(navController: NavController, blogViewModel: BlogViewModel) {
    val blogs by blogViewModel.blogs
    val isLoading by blogViewModel.isLoading
    val errorMessage by blogViewModel.errorMessage

    val nextPageToken = ""


    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val listState = rememberLazyListState()

// Cargar blogs si no hay más blogs o si el nextPageToken es null
    LaunchedEffect(nextPageToken) {
        if (nextPageToken != null) {
            blogViewModel.getBlogs()
        }
    }
    // Llamar a getBlogs() cuando la pantalla se compone
    LaunchedEffect(listState ) {
        blogViewModel.getBlogs()
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (lastVisibleItem >= totalItems - 3) { // Si quedan menos de 3 para el final
                    blogViewModel.getBlogs() // Llamar a cargar más
                }
            }
        /*snapshotFlow {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisibleItem?.index == totalItems - 1
        }.collect { isAtEnd ->
            if (isAtEnd && !isLoading && nextPageToken != null) {
                blogViewModel.getBlogs(pageToken=nextPageToken)
            }
        }*/
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
                        modifier = Modifier.padding(16.dp),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(blogs) { blog ->

                            AnimatedVisibility(
                                visible = true,
                                enter = slideInVertically(
                                    initialOffsetY = { it }
                                ) + fadeIn(animationSpec = tween(1000)) // Entrada con animación
                            ) {

                            BlogItem(
                                title = blog.title,
                                authorName = blog.author.displayName,
                                authorImage = blog.author.image.url
                            )
                        }

                            /*  Card(
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
                        */

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
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(fixImageUrl(authorImage))
                        .crossfade(true)
                        .build(),
                    contentDescription = "Imagen del autor",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 8.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
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
// Función para arreglar la URL si es relativa
fun fixImageUrl(url: String?): String {
    if (url.isNullOrEmpty()) return ""
    return if (url.startsWith("//")) {
        "https:$url"
    } else {
        url
    }
}
