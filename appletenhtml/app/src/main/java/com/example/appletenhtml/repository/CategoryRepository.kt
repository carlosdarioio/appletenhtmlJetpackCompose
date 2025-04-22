package com.example.appletenhtml.repository

import com.example.appletenhtml.api.CategoryApi
import com.example.appletenhtml.models.Category
import retrofit2.Response

class CategoryRepository(private val categoryApi: CategoryApi) {

    suspend fun getCategories(): Response<List<Category>> {
        return categoryApi.getCategories()
    }

    suspend fun getCategoryById(id: Int): Response<Category> {
        return categoryApi.getCategoryById(id)
    }

    suspend fun createCategory(category: Category): Response<Category> {
        return categoryApi.createCategory(category)
    }

    suspend fun updateCategory(id: Int, category: Category): Response<Category> {
        return categoryApi.updateCategory(id, category)
    }

    suspend fun deleteCategory(id: Int): Response<Category> {
        return categoryApi.deleteCategory(id)
    }
}
