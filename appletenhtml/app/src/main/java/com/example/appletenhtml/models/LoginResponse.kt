package com.example.appletenhtml.models

data class LoginResponse(
    val id: Int,
    val email: String,
    val name: String,
    val token: String
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val token: String
)
