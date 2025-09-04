package com.example.practicalistas2

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicalistas2.clases.Persona
import com.example.practicalistas2.ui.theme.PracticaListas2Theme

class PersonaDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val persona = intent.getSerializableExtra("persona") as? Persona
            ?: Persona(0, "", "", "")
        setContent {
            PracticaListas2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonForm(
                        persona = persona,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun PersonForm(persona: Persona, modifier: Modifier = Modifier) {
    val activity = (LocalContext.current as? Activity)
    Column (modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)){
        OutlinedTextField(
            value = persona.nombre,
            onValueChange = {},
            label = { Text("Nombre") },
            placeholder = { Text("Escribe el nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = persona.telefono,
            onValueChange = {},
            label = { Text("Telefono") },
            placeholder = { Text("Escribe el Telefono") },
        )
        OutlinedTextField(
            value = persona.direccion,
            onValueChange = {},
            label = { Text("Dirección") },
            placeholder = { Text("Escribe la dirección") }
        )
        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
        Button(
            onClick = {
                activity?.finish()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PracticaListas2Theme {
        PersonForm(Persona(12, "Oscar", "2347372", "Av Neuva"))
    }
}