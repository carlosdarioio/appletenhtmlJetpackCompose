package com.example.appletenhtml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appletenhtml.models.Category
import com.example.appletenhtml.repository.CategoryRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    fun getCategories() {
        viewModelScope.launch {
            val response: Response<List<Category>> = categoryRepository.getCategories()
            // Maneja la respuesta (puedes actualizar el estado aquí)
        }
    }

    fun getCategoryById(id: Int) {
        viewModelScope.launch {
            val response: Response<Category> = categoryRepository.getCategoryById(id)
            // Maneja la respuesta
        }
    }

    fun createCategory(category: Category) {
        viewModelScope.launch {
            val response: Response<Category> = categoryRepository.createCategory(category)
            // Maneja la respuesta
        }
    }

    fun updateCategory(id: Int, category: Category) {
        viewModelScope.launch {
            val response: Response<Category> = categoryRepository.updateCategory(id, category)
            // Maneja la respuesta
        }
    }

    fun deleteCategory(id: Int) {
        viewModelScope.launch {
            val response: Response<Category> = categoryRepository.deleteCategory(id)
            // Maneja la respuesta
        }
    }
}
