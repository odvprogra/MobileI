package com.example.ingredientsproject

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ingredientsproject.models.RecetasViewModel
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val repository: RecetasViewModel = viewModel(navController.getBackStackEntry("home"))
    val selectedIngredients = repository.selectedIngredients

    Column(modifier = modifier.padding(16.dp)) {
        Text("Selecciona Ingredientes", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(repository.getIngredientes()) { ingrediente ->
                val isSelected = selectedIngredients.contains(ingrediente.id)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) Color.Green.copy(alpha = 0.3f) else Color.Gray.copy(alpha = 0.1f))
                        .padding(8.dp)
                        .clickable {
                            if (isSelected) selectedIngredients.remove(ingrediente.id)
                            else selectedIngredients.add(ingrediente.id)
                        }
                ) {
                    Text(ingrediente.nombre)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Seleccionados: ${
                repository.getNombresIngredientes(selectedIngredients).joinToString()
            }"
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { selectedIngredients.clear() }, modifier = Modifier.weight(1f)) {
                Text("Limpiar")
            }
            Button(onClick = { navController.navigate("result") }, modifier = Modifier.weight(1f)) {
                Text("Buscar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenpreview() {
    val repository: RecetasViewModel = viewModel()

    Column() {
        Text("Selecciona Ingredientes", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(repository.getIngredientes()) { ingrediente ->
                val isSelected = repository.selectedIngredients.contains(ingrediente.id)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) Color.Green.copy(alpha = 0.3f) else Color.Gray.copy(alpha = 0.1f))
                        .padding(8.dp)
                        .clickable {
                            if (isSelected) repository.selectedIngredients.remove(ingrediente.id)
                            else repository.selectedIngredients.add(ingrediente.id)
                        }
                ) {
                    Text(ingrediente.nombre)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Seleccionados: ${
                repository.getNombresIngredientes(repository.selectedIngredients).joinToString()
            }"
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { repository.selectedIngredients.clear() }, modifier = Modifier.weight(1f)) {
                Text("Limpiar")
            }
            Button(onClick = { /* nadota licen*/ }, modifier = Modifier.weight(1f)) {
                Text("Buscar")
            }
        }
    }
}