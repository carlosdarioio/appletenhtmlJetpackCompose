package com.example.appletenhtml.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appletenhtml.datastore.DataStoreManager

import com.example.appletenhtml.datastore.UserPreferences
import com.example.appletenhtml.network.CategoryApi
import com.example.appletenhtml.network.RetrofitHelper
import com.example.appletenhtml.viewmodels.LoginViewModel
import com.example.appletenhtml.viewmodels.CategoryViewModel
import com.example.appletenhtml.views.*
import com.example.appletenhtml.network.LoginApi
import com.example.appletenhtml.views.CategoryListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()



    val context = LocalContext.current
    val loginApi = RetrofitHelper.getInstance().create(LoginApi::class.java)

    val userPreferences = UserPreferences(context)//<-cambiado por dataStoreManager
    val dataStoreManager = remember { DataStoreManager(context) }

    val loginViewModel = remember { LoginViewModel(loginApi, dataStoreManager) }

    val categoryApi = RetrofitHelper.getInstance().create(CategoryApi::class.java)
    val categoryViewModel = remember { CategoryViewModel(categoryApi) }

    var token by remember { mutableStateOf("") }

    LaunchedEffect (Unit) {
        dataStoreManager.getToken().collect {
            token = it.toString()
        }
    }




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

        //alola x aqui vas
        composable("category_list") {
            CategoryListScreen(navController = navController, categoryViewModel = categoryViewModel)
        }

        composable("category_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
            CategoryDetailScreen(id, navController, categoryViewModel)
        }



        /*composable("category_create") {
            CategoryCreateScreen(
                navController = navController,
                categoryViewModel = categoryViewModel,
                token = token, // Asegúrate de que esté disponible en AppNavigation
                dataStoreManager = dataStoreManager
            )
        }*/
        composable("category_edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
            CategoryEditScreen(navController, id, categoryViewModel,token)
        }




        /*
        composable("crud") { EjemploCRUD() }

         */
    }
}

