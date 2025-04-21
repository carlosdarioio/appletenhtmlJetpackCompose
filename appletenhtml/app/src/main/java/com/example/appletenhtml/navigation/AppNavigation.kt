package com.example.appletenhtml.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appletenhtml.MainMenu
import com.example.appletenhtml.views.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { MainMenu(navController) }
        composable("suma") { EjemploSuma() }
        /*
        composable("conversion") { EjemploConversion() }
        composable("edad") { EjemploEdad() }
        composable("bisiesto") { EjemploBisiesto() }
        composable("ciclo_for") { EjemploCicloFor() }
        composable("par_impar") { EjemploParImpar() }
        composable("login") { EjemploLogin() }
        composable("validacion") { EjemploValidacion() }
        composable("crud") { EjemploCRUD() }

         */
    }
}

