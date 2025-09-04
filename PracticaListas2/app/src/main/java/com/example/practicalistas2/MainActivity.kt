package com.example.practicalistas2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicalistas2.clases.Persona
import com.example.practicalistas2.ui.theme.PracticaListas2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaListas2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Cards(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun getPersonas(): ArrayList<Persona>{
    return arrayListOf(
        Persona(1, "Jorge David", "7788372", "Av. Piraí"),
        Persona(2, "Maria Camila", "738292", "Barrio Equipetrol"),
        Persona(3, "Marco Aurelio", "7788372", "Av. Piraí"),
        Persona(4, "Teresa", "738292", "Barrio Equipetrol"),
        Persona(5, "Ramon Valdez", "7382342", "Barrio Equipetrol")
    )
}

@Composable
fun Card(persona: Persona) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.clickable{
            val intent = Intent(context, PersonaDetailActivity::class.java)
            intent.putExtra("persona", persona)
            context.startActivity(intent)
        }
    ) {
        Text(persona.nombre)
        Text(persona.telefono)
        HorizontalDivider(
            modifier = Modifier.padding(0.dp, 8.dp)
        )
    }
}

@Composable
fun Cards(modifier:Modifier = Modifier){
    val personas = getPersonas()
    LazyColumn(
        modifier = modifier.background(Color(134, 112, 234, 255))
    ) {
        items(personas){
            it -> Card(it)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaListas2Theme {
        Cards()
    }
}