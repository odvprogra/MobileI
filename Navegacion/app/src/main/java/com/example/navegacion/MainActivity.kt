package com.example.navegacion

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacion.ui.theme.NavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavigationApp(modifier : Modifier = Modifier,navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = NavScreens.LOGIN.name
    ){
        composable(NavScreens.HOME.name){
            HomePage(modifier = modifier)
        }
        composable(NavScreens.LOGIN.name){
            LoginPage(modifier = modifier, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        Text(text = "Bienvenido crack", fontSize = 32.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    var usuario by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    Column(modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") },
                modifier = Modifier.weight(1f)
            )
        }

        Row {
            OutlinedTextField(
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.weight(1f)
            )
        }
        Button(
            onClick = {}
        ) {
            Text("Iniciar Sesion")
        }

    }
}



@Composable
fun HomePage(modifier:Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()) {
        Text(text = "Bienvenido crack", fontSize = 32.sp)
    }
}

@Composable
fun LoginPage(modifier:Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    var usuario by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") },
                modifier = Modifier.weight(1f)
            )
        }

        Row {
            OutlinedTextField(
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.weight(1f)
            )
        }
        Button(
            onClick = {
                if(USERS.containsKey(usuario) && USERS.get(usuario) == password){
                    navController.navigate(NavScreens.HOME.name)}
                else{
                    Toast.makeText(context, "Usuario incorrecto", Toast.LENGTH_LONG).show()
                }
            }


                ) {
                    Text("Iniciar Sesion")
                }

    }
}