package com.example.practicalistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.practicalistas.R
import com.example.practicalistas.ui.theme.Constants.IMAGE1_URL
import com.example.practicalistas.ui.theme.PracticaListasTheme
import com.example.practicalistas.Contact
import com.example.practicalistas.ui.theme.Constants.IMAGE2_URL
import com.example.practicalistas.ui.theme.Constants.IMAGE3_URL
import com.example.practicalistas.ui.theme.Constants.IMAGE4_URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaListasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Contactlist(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier) {
    val nameList = arrayListOf(
        "Ana", "Juan", "Pedro", "Maria", "Luis", "Carmen", "Jose", "Lucia", "Miguel", "Sofia"
    )
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(nameList) { it ->
            Text(text = it, modifier = modifier.padding(8.dp))
        }
    }
}

@Composable
fun Contactlist(
    modifier: Modifier = Modifier
) {
    val contacts = getContactList()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(contacts) { it ->
            ContactItem(it)
            HorizontalDivider(thickness = 1.dp)
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Spacer(modifier = Modifier.size(8.dp))
    Row {
        AsyncImage(
            model = contact.image,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Text(
            contact.name + " " + contact.lastName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    Spacer(modifier = Modifier.size(8.dp))
}

fun getContactList(): ArrayList<Contact> {
    return arrayListOf(
        Contact(1, "Ana", "Perez", "123456789", IMAGE1_URL),
        Contact(2, "Maria", "Perez", "123456789", IMAGE1_URL),
        Contact(3, "Raul", "Perez", "123456789", IMAGE3_URL),
        Contact(4, "Sergio", "Perez", "123456789", IMAGE4_URL),
        Contact(5, "Erick", "Perez", "123456789", IMAGE2_URL),
        Contact(6, "Marquino", "Perez", "123456789", IMAGE3_URL),
        Contact(7, "Jose", "Perez", "123456789", IMAGE4_URL),
        Contact(8, "Cristina", "Perez", "123456789", IMAGE1_URL),
        Contact(9, "Maria Jose", "Perez", "123456789", IMAGE1_URL),
        Contact(10, "Sebastian", "Perez", "123456789", IMAGE4_URL),
        Contact(11, "Angel", "Perez", "123456789", IMAGE2_URL),
        Contact(12, "Pedro", "Perez", "123456789", IMAGE3_URL),
        Contact(13, "Juan", "Perez", "123456789", IMAGE4_URL),
        Contact(14, "Celeste", "Perez", "123456789", IMAGE1_URL)

    )
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    PracticaListasTheme {
        Contactlist()
    }
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    PracticaListasTheme {
        ContactItem(Contact(1, "Ana", "Perez", "123456789", IMAGE1_URL))
    }
}


@Preview(showBackground = true)
@Composable
fun MessageListPreview() {
    PracticaListasTheme {
        MessageList()
    }
}