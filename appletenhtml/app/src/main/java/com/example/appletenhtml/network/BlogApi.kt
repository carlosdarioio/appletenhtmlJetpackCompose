package com.example.appletenhtml.network

import com.example.appletenhtml.models.Blog
import com.example.appletenhtml.models.BlogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlogApi {

    @GET("blog")
    suspend fun getBlogs(
        @Query("q") query: String? = "*",
        @Query("pageToken") pageToken: String? = "*"
    ): Response<BlogResponse>

    @GET("blog/show/{id}")
    suspend fun getBlogById(
        @Path("id") id: String
    ): Response<Blog>
}


