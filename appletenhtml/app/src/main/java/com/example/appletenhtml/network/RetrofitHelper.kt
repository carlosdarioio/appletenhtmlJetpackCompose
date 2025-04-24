package com.example.appletenhtml.network

import com.example.appletenhtml.network.LoginApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://cdfn3.com/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    object RetrofitHelper {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://cdfn3.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getLoginApi(): LoginApi {
            return retrofit.create(LoginApi::class.java)
        }
    }
}
