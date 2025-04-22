package com.example.appletenhtml.models

import com.squareup.moshi.Json

data class Category(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val status: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)
