package com.example.appletenhtml.models

data class ApiResponseError(
    val message: String,
    val errors: Map<String, List<String>>?
)