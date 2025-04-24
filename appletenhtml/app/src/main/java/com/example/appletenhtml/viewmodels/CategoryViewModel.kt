package com.example.appletenhtml.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appletenhtml.models.Category
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

    fun deleteCategory(id: Int) {
        viewModelScope.launch {
            try {
                val response = api.deleteCategory(id)
                if (response.isSuccessful) {
                    getAllCategories()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
