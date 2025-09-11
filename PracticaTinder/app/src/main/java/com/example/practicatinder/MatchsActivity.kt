package com.example.practicatinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicatinder.models.Persona
import com.example.practicatinder.ui.theme.PracticaTinderTheme

class MatchsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaTinderTheme {
                val persona = intent.getSerializableExtra("persona") as? Persona
                    ?: Persona("", "", 0, listOf(),"")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MatchsCard(persona = persona, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MatchsCard(persona: Persona, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().background(Color.Black).padding(22.dp), verticalArrangement = Arrangement.spacedBy(12.dp)){
        Text("Tus matchs:", fontSize = 32.sp, color=Color.White)
        Column (){
            Text("${persona.nombre} ${persona.apellido} ${persona.edad}", color = Color.White)
            Text("Juan Quispe 32", color = Color.White)
            Text("Maria Pedriel 23", color=Color.White)
            Text("Teresa Ramos 21", color = Color.White)
            Text("Marco Letelier 19", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaTinderTheme {
        MatchsCard(Persona("Pedro", "Velaques",33, listOf(), ""))
    }
}