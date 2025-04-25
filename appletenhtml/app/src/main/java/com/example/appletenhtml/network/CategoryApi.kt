package com.example.appletenhtml.network

import com.example.appletenhtml.models.Category
import com.example.appletenhtml.models.CategoryRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Header

interface CategoryApi {
    @GET("categoriesDemo")
    suspend fun getCategories(): Response<List<Category>>

    @GET("categoriesDemo/{id}")
    suspend fun getCategory(@Path("id") id: Int): Response<Category>

    @GET("categoriesDemo2/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): Response<Category>

    @POST("categoriesDemo/store")
    suspend fun createCategory(
        @Header("Authorization") token: String,
        @Body category: CategoryRequest
    ): Response<Category>

    @POST("categoriesDemo/update/{id}")
    suspend fun updateCategory(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body category: Category
    ): Response<Category>

    @POST("categoriesDemo/destroy/{id}")
    suspend fun deleteCategory(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Map<String, String>>
}