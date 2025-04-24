package com.example.appletenhtml.network

import com.example.appletenhtml.network.LoginApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://cdfn3.com/api/"

    val api: LoginApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://cdfn3.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
}
