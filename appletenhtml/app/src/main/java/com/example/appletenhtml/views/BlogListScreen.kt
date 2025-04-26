package com.example.appletenhtml.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Blog") }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
        ) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    blogViewModel.searchBlogs(it.text)
                },
                label = { Text("Buscar") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(8.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(blogs) { blog ->
                    BlogItem(blog.title, blog.author.displayName, blog.author.image.url)
                }

                item {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp)
                        )
                    } else if (blogs.isNotEmpty()) {
                        Button(
                            onClick = { blogViewModel.loadMore() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text("Cargar más")
                        }
                    }
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
