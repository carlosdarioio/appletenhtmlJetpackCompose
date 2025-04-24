package com.example.appletenhtml.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appletenhtml.views.ParImparScreen
import com.example.appletenhtml.MainMenu
import com.example.appletenhtml.dataStore.DataStoreManager
import com.example.appletenhtml.datastore.UserPreferences
import com.example.appletenhtml.network.RetrofitHelper
import com.example.appletenhtml.viewmodels.LoginViewModel
import com.example.appletenhtml.views.*
import com.example.appletenhtml.network.LoginApi
import com.example.appletenhtml.ui.CategoryListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    val context = LocalContext.current
    val loginApi = RetrofitHelper.getInstance().create(LoginApi::class.java)

    val userPreferences = UserPreferences(context)//<-cambiado por dataStoreManager
    val dataStoreManager = remember { DataStoreManager(context) }

    val loginViewModel = remember { LoginViewModel(loginApi, dataStoreManager) }



    NavHost(navController = navController, startDestination = "home") {
        //composable("home") { MainMenu(navController) }
        composable("home"){ HomeScreen(navController) }
        composable("suma") { SumaScreen(navController) }
        composable("conversion") { EjemploConversion(navController) }
        composable("edad") { EdadScreen(navController) }
        composable("bisiesto") { BisiestoScreen(navController) }
        composable("ciclo_for") { TablaForScreen(navController) }
        composable("par_impar") { ParImparScreen(navController) }
        composable("login") { LoginScreen(navController,loginViewModel) }

        alola x aqui vas
        composable("categoryList") {
            CategoryListScreen(navController = navController, categoryViewModel = categoryViewModel)
        }



        /*
        composable("crud") { EjemploCRUD() }

         */
    }
}

