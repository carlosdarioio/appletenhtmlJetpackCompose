package com.example.appletenhtml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appletenhtml.models.Category
import com.example.appletenhtml.models.CategoryRequest
import com.example.appletenhtml.network.CategoryApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val api: CategoryApi) : ViewModel() {

    //private val _categories = mutableStateOf<List<Category>>(emptyList())
    //val categories: State<List<Category>> = _categories

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories


    fun getAllCategories() {
        viewModelScope.launch {
            try {
                val response = api.getCategories()
                if (response.isSuccessful) {
                    _categories.value = response.body() ?: emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createCategory(nombre: String, descripcion: String, token: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                //val body = mapOf("nombre" to nombre, "descripcion" to descripcion)
                val categoryRequest = CategoryRequest(nombre = nombre, descripcion = descripcion)
                val response = api.createCategory("Bearer $token",categoryRequest)
                if (response.isSuccessful) {
                    getAllCategories()
                    onSuccess()
                } else {
                    val error = response.errorBody()?.string() ?: "Error desconocido"
                    onError(error)
                }
            } catch (e: Exception) {
                onError(e.message ?: "Error desconocido")
            }
        }
    }


    fun deleteCategory(token:String,id: Int) {
        viewModelScope.launch {
            try {
                val response = api.deleteCategory(token="",id = id)
                if (response.isSuccessful) {
                    getAllCategories()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
