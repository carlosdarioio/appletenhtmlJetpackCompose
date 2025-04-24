package com.example.appletenhtml.network

import com.example.appletenhtml.models.LoginRequest
import com.example.appletenhtml.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/*interface LoginApi {

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}*/

interface LoginApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<User>
}
