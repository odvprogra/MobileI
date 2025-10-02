package com.example.ingredientsproject

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ingredientsproject.models.RecetasViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun DetailScreen(
    navController: NavHostController,
    recetaId: Int,
    modifier: Modifier = Modifier
) {
    val viewModel: RecetasViewModel = viewModel(navController.getBackStackEntry("home"))
    val receta = remember(recetaId) { viewModel.getRecetaById(recetaId) }

    Column(modifier = modifier.padding(16.dp)) {
        if (receta != null) {
            Text("Nombre: ${receta.nombre}", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))

            Text("Ingredientes:", style = MaterialTheme.typography.titleMedium)
            LazyColumn(modifier = Modifier.height(150.dp)) {
                items(viewModel.getNombresIngredientes(receta.ingredientes)) { nombreIngrediente ->
                    Text("- $nombreIngrediente")
                }
            }

            Spacer(Modifier.height(16.dp))

            Text("Preparación:", style = MaterialTheme.typography.titleMedium)
            Text(receta.preparacion)

        } else {
            Text("Receta no encontrada")
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(
) {
    val viewModel: RecetasViewModel = viewModel()
    val receta = remember(1) { viewModel.getRecetaById(1) }

    Column(modifier = Modifier.padding(16.dp)) {
        if (receta != null) {
            Text("Nombre: ${receta.nombre}", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))

            Text("Ingredientes:", style = MaterialTheme.typography.titleMedium)
            LazyColumn(modifier = Modifier.height(150.dp)) {
                items(viewModel.getNombresIngredientes(receta.ingredientes)) { nombreIngrediente ->
                    Text("- $nombreIngrediente")
                }
            }

            Spacer(Modifier.height(16.dp))

            Text("Preparación:", style = MaterialTheme.typography.titleMedium)
            Text(receta.preparacion)

        } else {
            Text("Receta no encontrada")
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = { }) {
            Text("Volver")
        }
    }
}
