package com.example.appletenhtml.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appletenhtml.datastore.DataStoreManager

import com.example.appletenhtml.datastore.UserPreferences
import com.example.appletenhtml.network.BlogApi
import com.example.appletenhtml.network.CategoryApi
import com.example.appletenhtml.network.RetrofitHelper
import com.example.appletenhtml.viewmodels.LoginViewModel
import com.example.appletenhtml.viewmodels.CategoryViewModel
import com.example.appletenhtml.views.*
import com.example.appletenhtml.network.LoginApi
import com.example.appletenhtml.viewmodels.BlogViewModel
import com.example.appletenhtml.viewmodels.BlogViewModelFactory
import com.example.appletenhtml.viewmodels.ThemeViewModel
import com.example.appletenhtml.views.CategoryListScreen

@Composable
fun AppNavigation(navController: NavHostController,
                  themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()



    val context = LocalContext.current
    val loginApi = RetrofitHelper.getInstance().create(LoginApi::class.java)

    val userPreferences = UserPreferences(context)//<-cambiado por dataStoreManager
    val dataStoreManager = remember { DataStoreManager(context) }

    val loginViewModel = remember { LoginViewModel(loginApi, dataStoreManager) }

    val categoryApi = RetrofitHelper.getInstance().create(CategoryApi::class.java)
    val categoryViewModel = remember { CategoryViewModel(categoryApi) }

    var token by remember { mutableStateOf("") }

    // Blog
    val blogApi = RetrofitHelper.getRetrofitInstance().create(BlogApi::class.java)
    val blogViewModel: BlogViewModel = viewModel(factory = BlogViewModelFactory(blogApi))


    LaunchedEffect (Unit) {
        dataStoreManager.getToken().collect {
            token = it.toString()
        }
    }




    NavHost(navController = navController, startDestination = "home") {
        //composable("home") { MainMenu(navController) }
        composable("home"){
            MainScreen(navController) {
                HomeScreen(navController)
            }
        }
        composable("suma") {
            MainScreen(navController) {
                SumaScreen(navController)
            }
        }
        composable("conversion") {
            MainScreen(navController) {
                EjemploConversion(navController)
            }
        }
        composable("edad") {
            MainScreen(navController) {
                EdadScreen(navController)
            }

        }
        composable("bisiesto") {
            MainScreen(navController) {
                BisiestoScreen(navController)
            }

        }
        composable("ciclo_for") {
            MainScreen(navController) {
                TablaForScreen(navController)
            }
        }
        composable("par_impar") {
            MainScreen(navController) {
                ParImparScreen(navController)
            }
        }
        composable("login") {
            MainScreen(navController) {
            LoginScreen(navController,loginViewModel,themeViewModel)
            }
        }

        composable("category_list") {

            MainScreen(navController) {
            CategoryListScreen(
                navController = navController,
                categoryViewModel = categoryViewModel,
                themeViewModel
            )
            }
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

        composable("blogList") {
            MainScreen(navController) {
                BlogListScreen(navController, blogViewModel)
            }
        }


        composable("advanced_form") {
            MainScreen(navController) {
                AdvancedFormScreen(navController)
            }
        }




        /*
        composable("crud") { EjemploCRUD() }

         */
    }
}

