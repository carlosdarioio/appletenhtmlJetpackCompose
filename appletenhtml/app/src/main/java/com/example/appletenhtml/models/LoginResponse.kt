package com.example.appletenhtml.models

data class LoginResponse(
    val message: String,
    val loggin_token: String,
    val user: User
)

data class User(
    val id: Int,
    val name: String,
    val last_name: String,
    val email: String,
    val phone: String?,
    val i_status: String?
)
