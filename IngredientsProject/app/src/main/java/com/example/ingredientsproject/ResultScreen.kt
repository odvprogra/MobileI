package com.example.ingredientsproject

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(navController: NavHostController, modifier: Modifier) {
    Column(modifier = modifier) {
        Text("Pantalla Detalle")
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    Column(modifier = Modifier) {
        Text("Pantalla Detalle")
        Button(onClick = { }) {
            Text("Volver")
        }
    }
}