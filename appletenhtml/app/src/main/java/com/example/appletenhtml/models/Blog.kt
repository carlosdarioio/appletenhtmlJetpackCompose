package com.example.appletenhtml.models

data class Blog(
    val id: String,
    val title: String,
    val content: String,
    val published: String,
    val updated: String,
    val url: String,
    val author: Author
)

data class Author(
    val id: String,
    val displayName: String,
    val url: String,
    val image: Image
)

data class Image(
    val url: String
)