package com.example.appletenhtml.api

import com.example.appletenhtml.models.Category
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoryApi {

    @GET("categoriesDemo")
    suspend fun getCategories(): Response<List<Category>>

    @GET("categoriesDemo/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): Response<Category>

    @POST("categoriesDemo/store")
    suspend fun createCategory(@Body category: Category): Response<Category>

    @POST("categoriesDemo/update/{id}")
    suspend fun updateCategory(
        @Path("id") id: Int,
        @Body category: Category
    ): Response<Category>

    @POST("categoriesDemo/destroy/{id}")
    suspend fun deleteCategory(@Path("id") id: Int): Response<Category>
}
