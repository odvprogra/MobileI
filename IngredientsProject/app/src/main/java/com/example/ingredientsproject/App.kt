package com.example.ingredientsproject

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun App(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, modifier) }
        composable("result") { ResultScreen(navController, modifier) }
        composable("create") { CreateScreen(navController, modifier) }
        composable("details/{recetaId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("recetaId")?.toIntOrNull() ?: 0
            DetailScreen(navController, recetaId = id, modifier)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, Modifier) }
        composable("busqueda") { ResultScreen(navController, Modifier) }
    }
}