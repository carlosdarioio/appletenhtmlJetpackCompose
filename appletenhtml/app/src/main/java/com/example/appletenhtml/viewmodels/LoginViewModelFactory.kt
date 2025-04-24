package com.example.appletenhtml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appletenhtml.dataStore.DataStoreManager
import com.example.appletenhtml.network.LoginApi

class LoginViewModelFactory(
    private val loginApi: LoginApi,
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginApi, dataStoreManager) as T
    }
}
