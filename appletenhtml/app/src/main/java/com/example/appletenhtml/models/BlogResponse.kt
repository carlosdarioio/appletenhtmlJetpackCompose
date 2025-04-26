package com.example.appletenhtml.models

data class BlogResponse(
    val items: List<Blog>,
    val nextPageToken: String? = null
)