package com.example.appletenhtml.models

data class LoginResponse(
    val id: Int,
    val email: String,
    val name: String,
    val token: String
)

