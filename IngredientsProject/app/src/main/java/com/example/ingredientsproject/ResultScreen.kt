package com.example.ingredientsproject

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
import com.example.ingredientsproject.models.Receta
import com.example.ingredientsproject.models.RecetasViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun ResultScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: RecetasViewModel = viewModel(navController.getBackStackEntry("home"))
    val recetasEncontradas = remember(viewModel.selectedIngredients.toList()) {
        viewModel.buscarRecetasPorIngredientes(viewModel.selectedIngredients.toList())
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Resultados de búsqueda", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Ingredientes seleccionados: ${viewModel.getNombresIngredientes(viewModel.selectedIngredients).joinToString()}",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(16.dp))

        if (recetasEncontradas.isEmpty()) {
            Text("No hay recetas ¿Desea agregarla?")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navController.navigate("create") }) {
                Text("Agregar receta")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(recetasEncontradas) { receta ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("details/${receta.id}") }
                            .padding(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("ID: ${receta.id}", style = MaterialTheme.typography.bodyMedium)
                            Text("Nombre: ${receta.nombre}", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreen() {
    val viewModel: RecetasViewModel = viewModel()
    val recetasEncontradas = listOf<Receta>()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Resultados de búsqueda", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        if (recetasEncontradas.isEmpty()) {
            Text("No hay recetas con esos ingredientes")
            Spacer(Modifier.height(16.dp))
            Button(onClick = {  }) {
                Text("Agregar receta")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(recetasEncontradas) { receta ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {}
                            .padding(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("ID: ${receta.id}", style = MaterialTheme.typography.bodyMedium)
                            Text("Nombre: ${receta.nombre}", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {  }) {
            Text("Volver")
        }
    }
}