package com.example.practicainstagram_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicainstagram_1.ui.theme.CatsURLs.CAT_1
import com.example.practicainstagram_1.ui.theme.PracticaInstagram_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaInstagram_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Instagram(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Story() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        AsyncImage(
            model = CAT_1,
            contentDescription = "a",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            "Maria",
            fontSize = 14.sp

        )
    }

}

@Composable
fun Post(modifier: Modifier = Modifier){
    Column(){
        Row (Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)){
            AsyncImage(
                model = CAT_1,
                contentDescription = "a",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(
                "Gato",
                fontSize = 14.sp

            )
        }
        AsyncImage(
            model = CAT_1,
            contentDescription = "a",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column {
            Text("Icons here")
        }

    }
}


@Composable
fun Posts(modifier: Modifier = Modifier){
    LazyColumn(){
        items(5){
            Post()
        }
    }
}

@Composable
fun Stories(modifier: Modifier = Modifier){
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(5){
            Story()
        }
    }
}

@Composable
fun Instagram(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ){
        Stories()
        Posts()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaInstagram_1Theme {
        Instagram()
    }
}