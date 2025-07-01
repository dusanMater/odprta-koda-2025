package com.example.vaja05nal02seznamnakup

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class Artikel(val ime: String, val vKosarici: Boolean)

@Serializable
data class Shramba(val seznam: List<Artikel>, val datum: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NakupovalniSeznam(this)
        }
    }
}

@Composable
fun NakupovalniSeznam(context: Context) {
    var imeArtikla by remember { mutableStateOf("") }
    val seznam = remember { mutableStateListOf<Artikel>() }
    var datumShranjevanja by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = imeArtikla,
            onValueChange = { imeArtikla = it },
            label = { Text("Vnesi artikel") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (imeArtikla.isNotBlank()) {
                    seznam.add(Artikel(imeArtikla, false))
                    imeArtikla = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dodaj artikel")
        }

        Button(
            onClick = {
                val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                val zdaj = sdf.format(Date())
                datumShranjevanja = zdaj
                val shramba = Shramba(seznam.toList(), zdaj)
                val json = Json.encodeToString(shramba)
                context.openFileOutput("nakup.json", Context.MODE_PRIVATE).use {
                    it.write(json.toByteArray())
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Shrani na disk")
        }

        Button(
            onClick = {
                try {
                    val vsebina = context.openFileInput("nakup.json").bufferedReader().readText()
                    val shramba = Json.decodeFromString<Shramba>(vsebina)
                    seznam.clear()
                    seznam.addAll(shramba.seznam)
                    datumShranjevanja = shramba.datum
                } catch (_: Exception) {
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Naloži iz diska")
        }

        Button(
            onClick = {
                seznam.clear()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Izbriši seznam")
        }

        if (datumShranjevanja.isNotEmpty()) {
            Text("Zadnje shranjeno: $datumShranjevanja", fontSize = 14.sp)
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            itemsIndexed(seznam) { index, artikel ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            seznam[index] = artikel.copy(vKosarici = !artikel.vKosarici)
                        }
                        .padding(8.dp), 
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = artikel.ime,
                        style = if (artikel.vKosarici)
                            TextStyle(textDecoration = TextDecoration.LineThrough)
                        else
                            TextStyle.Default,
                        fontSize = 18.sp
                    )
                    if (artikel.vKosarici) {
                        Text("✔", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}