package com.example.appletenhtml.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appletenhtml.views.EjemploParImpar
import com.example.appletenhtml.MainMenu
import com.example.appletenhtml.views.*
import com.www.appletenhtml.network.LoginApi

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { MainMenu(navController) }
        composable("suma") { EjemploSuma() }
        composable("conversion") { EjemploConversion() }
        composable("edad") { EjemploEdad() }
        composable("bisiesto") { EjemploBisiesto() }
        composable("ciclo_for") { EjemploCicloFor() }
        composable("par_impar") { EjemploParImpar() }
        composable("login") { LoginScreen(navController) }
        //Ejemplo de login/auth
        composable(
            route = "home/{name}/{lastName}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("lastName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val lastName = backStackEntry.arguments?.getString("lastName") ?: ""
            HomeScreen(name = name, lastName = lastName)
        }
        /*
        composable("validacion") { EjemploValidacion() }
        composable("crud") { EjemploCRUD() }

         */
    }
}

