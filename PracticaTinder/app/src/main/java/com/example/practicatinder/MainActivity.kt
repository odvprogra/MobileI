package com.example.practicatinder

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicatinder.models.Persona
import com.example.practicatinder.models.personas
import com.example.practicatinder.ui.theme.PracticaTinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaTinderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VistaPrincipal(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun VistaPrincipal(modifier: Modifier = Modifier){
    var currentIndex by remember { mutableStateOf(0) }
    val currentPersona = personas[currentIndex]
    Column(modifier = modifier.fillMaxSize().background(Color.Black)) {
        HeaderView()
        PhotosView(Modifier.weight(1f), currentPersona)
        ButtonsPanel(currentPersona, onNext = {
            currentIndex = (currentIndex + 1) % personas.size
        })
    }
}


@Composable
fun HeaderView(){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        modifier = Modifier.fillMaxWidth().background(Color.Black).padding(8.dp, 12.dp)){
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)
        , verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier.size(28.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo_text),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier.size(74.dp, 35.dp)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(18.dp)){
            Image(
                painter = painterResource(id = R.drawable.campana),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier.size(22.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ajustes),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier.size(22.dp)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    PracticaTinderTheme {
            HeaderView()
    }
}

@Composable
fun PhotosView(modifier: Modifier = Modifier, persona: Persona) {
    val images = persona.fotos
    var currentIndex by remember { mutableStateOf(0) }
    Column(modifier = modifier){
        Box(
            modifier = Modifier
                .fillMaxWidth().
                    weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .clickable{
                    currentIndex = (currentIndex+1) % images.size
                },
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = images[currentIndex]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        InformationPanel(persona)
    }
}

@Composable
fun ButtonsPanel(persona: Persona, onNext: () -> Unit) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp, 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.x),
            contentDescription = "Nope",
            modifier = Modifier
                .size(74.dp, 35.dp)
                .clickable {
                    onNext()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.green_hearth),
            contentDescription = "Match",
            modifier = Modifier
                .size(74.dp, 35.dp)
                .clickable {
                    // Ejemplo: abrir MatchsActivity con la persona actual
                    val intent = Intent(context, MatchsActivity::class.java)
                    intent.putExtra("persona", persona)
                    context.startActivity(intent)
                }
        )
    }
}


@Composable
fun InformationPanel(persona:Persona) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black)
                )
            ).padding(12.dp)
    ) {
        Text("${persona.nombre} ${persona.apellido} ${persona.edad}", fontSize = 26.sp, color = Color.White)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            color = Color.White,
            shadowElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("About me", fontSize = 17.sp, color = Color.White)
                Text(
                    persona.description,
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }
    }

}