package com.example.vaja06naloga01kosarica.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vaja06naloga01kosarica.R
import com.example.vaja06naloga01kosarica.model.Artikel
import com.example.vaja06naloga01kosarica.loadStudentsListFromFile
import com.example.vaja06naloga01kosarica.saveArtikelToFile

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {
    var a by rememberSaveable { mutableStateOf("") }
    var artikli = remember { mutableStateListOf<Artikel>() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val art = loadStudentsListFromFile(context)
        artikli.clear()
        artikli.addAll(art)
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(8.dp)
            .background(Color(0xFFfc4503))
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = a,
            onValueChange = { a = it },
            label = { Text("Vpiši artikel") }
        )

        Button(
            onClick = {
                if (a.isNotEmpty()) {
                    artikli.add(Artikel(a, false))
                    a = ""
                } else {
                    Toast.makeText(context, "Vnesi podatke v vnosno polje", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Dodaj na seznam")
        }

        LazyColumn {
            items(artikli) { artikel ->
                Column {
                    Row(
                        modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    val index = artikli.indexOf(artikel)
                                    if (index >= 0)
                                        artikli[index] = artikel.copy(vKosarici = !artikel.vKosarici)
                                }
                            )
                    ) {
                        Text(
                            artikel.ime,
                            fontSize = 24.sp,
                            textDecoration = if (artikel.vKosarici) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.weight(1f)
                        )

                        if (artikel.vKosarici) {
                            Image(
                                painter = painterResource(id = R.drawable.kljukica),
                                contentDescription = "Slika kljukice",
                                modifier = Modifier.width(24.dp)
                            )
                        }
                    }

                    HorizontalDivider(
                        color = Color.Black,
                        thickness = 2.dp
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                saveArtikelToFile(context, artikli)
            }) {
                Text("Shrani v dat")
            }

            Button(onClick = {
                val art = loadStudentsListFromFile(context)
                artikli.clear()
                artikli.addAll(art)
            }) {
                Text("Priklici v dat")
            }

            Button(onClick = {
                artikli.clear()
            }) {
                Text("Briši seznam")
            }
        }

        Button(
            onClick = { navController.navigate("history") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Poglej zgodovino")
        }
    }
}