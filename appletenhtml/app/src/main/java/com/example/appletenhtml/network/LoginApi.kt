package com.www.appletenhtml.network

import com.example.appletenhtml.models.LoginRequest
import com.example.appletenhtml.models.LoginResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @Headers("Content-Type: application/json")
    @POST("/api/login")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}
