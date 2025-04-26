package com.example.appletenhtml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appletenhtml.network.BlogApi

class BlogViewModelFactory(private val blogApi: BlogApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BlogViewModel(blogApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
