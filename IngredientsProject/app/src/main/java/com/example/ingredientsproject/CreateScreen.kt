package com.example.ingredientsproject

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ingredientsproject.models.Ingrediente
import com.example.ingredientsproject.models.Receta
import com.example.ingredientsproject.models.RecetasViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: RecetasViewModel = viewModel(navController.getBackStackEntry("home"))

    var nombreReceta by remember { mutableStateOf("") }
    var preparacion by remember { mutableStateOf("") }
    var nuevoIngrediente by remember { mutableStateOf("") }
    val ingredientesSeleccionados = remember { mutableStateListOf<Int>() }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Crear Nueva Receta", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = nombreReceta,
            onValueChange = { nombreReceta = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = preparacion,
            onValueChange = { preparacion = it },
            label = { Text("Preparación") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(Modifier.height(16.dp))

        Text("Agregar Nuevo Ingrediente", style = MaterialTheme.typography.titleMedium)

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = nuevoIngrediente,
                onValueChange = { nuevoIngrediente = it },
                label = { Text("Nuevo ingrediente") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (nuevoIngrediente.isNotBlank() && !viewModel.existeIngrediente(nuevoIngrediente)) {
                        viewModel.agregarIngrediente(nuevoIngrediente)
                        nuevoIngrediente = ""
                    }
                }
            ) {
                Text("Agregar")
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Seleccionar Ingredientes", style = MaterialTheme.typography.titleMedium)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.getIngredientes()) { ingrediente ->
                val isSelected = ingredientesSeleccionados.contains(ingrediente.id)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) Color.Green.copy(alpha = 0.3f) else Color.Gray.copy(alpha = 0.1f))
                        .padding(8.dp)
                        .clickable {
                            if (isSelected) ingredientesSeleccionados.remove(ingrediente.id)
                            else ingredientesSeleccionados.add(ingrediente.id)
                        }
                ) {
                    Text(ingrediente.nombre)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Ingredientes seleccionados: ${
                viewModel.getNombresIngredientes(ingredientesSeleccionados).joinToString()
            }"
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }
            Button(
                onClick = {
                    if (nombreReceta.isNotBlank() &&
                        preparacion.isNotBlank() &&
                        ingredientesSeleccionados.isNotEmpty()) {

                        viewModel.agregarReceta(
                            nombre = nombreReceta,
                            ingredientesIds = ingredientesSeleccionados.toList(),
                            preparacion = preparacion
                        )

                        navController.popBackStack()
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = nombreReceta.isNotBlank() &&
                        preparacion.isNotBlank() &&
                        ingredientesSeleccionados.isNotEmpty()
            ) {
                Text("Guardar")
            }
        }
    }
}