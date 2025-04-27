package com.example.appletenhtml.models
data class BlogData(
    val kind: String,
    val nextPageToken: String?,
    val items: List<Blog>,
    val etag: String
)

data class Blog(
    val kind: String,
    val id: String,
    val published: String,
    val updated: String,
    val url: String,
    val selfLink: String,
    val title: String,
    val content: String,
    val author: Author,
    val replies: Replies,
    val labels: List<String>?,
    val etag: String
)

data class Author(
    val id: String,
    val displayName: String,
    val url: String,
    val image: AuthorImage
)

data class AuthorImage(
    val url: String
)
data class Replies(
    val totalItems: String,
    val selfLink: String
)