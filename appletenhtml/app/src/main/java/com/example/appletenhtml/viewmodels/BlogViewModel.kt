package com.example.appletenhtml.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appletenhtml.models.Blog
import com.example.appletenhtml.network.BlogApi
import kotlinx.coroutines.launch

class BlogViewModel(private val api: BlogApi) : ViewModel() {

    var blogs = mutableStateOf<List<Blog>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private var nextPageToken: String? = null
    private var currentQuery: String? = null

    fun getBlogs(reset: Boolean = false, query: String? = null) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                if (reset) {
                    blogs.value = emptyList()
                    nextPageToken = null
                }

                val response = api.getBlogs(query = query ?: currentQuery, pageToken = nextPageToken)

                if (response.isSuccessful) {
                    response.body()?.let { blogResponse ->
                        nextPageToken = blogResponse.nextPageToken
                        currentQuery = query

                        blogs.value = blogs.value + (blogResponse.items ?: emptyList())
                    }
                } else {
                    errorMessage.value = "Error cargando blogs"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun loadMore() {
        if (nextPageToken != null && !isLoading.value) {
            getBlogs()
        }
    }

    fun searchBlogs(query: String) {
        getBlogs(reset = true, query = query)
    }

    fun clearError() {
        errorMessage.value = null
    }
}
