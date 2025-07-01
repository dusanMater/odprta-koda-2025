package com.example.vaja05nal01

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File


@Serializable
data class Student(val ime: String, val priimek: String, val spol: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StudentApp(this) }
    }
}

@Composable
fun StudentApp(context: Context) {
    var ime by remember { mutableStateOf("") }
    var priimek by remember { mutableStateOf("") }
    var spol by remember { mutableStateOf("M") }

    val seznam = remember { mutableStateListOf<Student>() }
    var izbranIndex by remember { mutableStateOf(-1) }
    var zadnjiKlik by remember { mutableStateOf(0L) }

    // Ob zagonu aplikacije prebere JSON datoteko in napolni seznam
    LaunchedEffect(Unit) {
        val prebrani = preberiIzDatoteke(context)
        seznam.clear()
        seznam.addAll(prebrani)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(value = ime, onValueChange = { ime = it }, label = { Text("Ime") })
        OutlinedTextField(value = priimek, onValueChange = { priimek = it }, label = { Text("Priimek") })

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Row {
                RadioButton(selected = spol == "M", onClick = { spol = "M" }); Text("Moški")
            }
            Row {
                RadioButton(selected = spol == "Ž", onClick = { spol = "Ž" }); Text("Ženska")
            }
        }

        // Gumb: Dodaj ali posodobi
        if (izbranIndex == -1) {
            Button(onClick = {
                if (ime.isNotBlank() && priimek.isNotBlank()) {
                    seznam.add(Student(ime, priimek, spol))
                    ime = ""; priimek = ""; spol = "M"
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Dodaj študenta")
            }
        } else {
            Button(onClick = {
                seznam[izbranIndex] = Student(ime, priimek, spol)
                ime = ""; priimek = ""; spol = "M"; izbranIndex = -1
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Posodobi študenta")
            }
        }

        // Gumb: Shrani
        Button(onClick = {
            shraniVDatoteko(context, seznam)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Shrani v datoteko")
        }

        // Gumb: Izbriši seznam
        Button(onClick = {
            seznam.clear()
            izbranIndex = -1
            ime = ""; priimek = ""; spol = "M"
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Izbriši vse iz seznama")
        }

        // Gumb: Izbriši datoteko
        Button(onClick = {
            izbrisiDatoteko(context)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Izbriši datoteko")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(seznam) { index, student ->
                Text(
                    text = "${student.ime} ${student.priimek} (${student.spol})",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val zdaj = System.currentTimeMillis()
                            if (izbranIndex == index && zdaj - zadnjiKlik < 500) {
                                seznam.removeAt(index)
                                izbranIndex = -1; ime = ""; priimek = ""; spol = "M"
                            } else {
                                izbranIndex = index
                                ime = student.ime; priimek = student.priimek; spol = student.spol
                                zadnjiKlik = zdaj
                            }
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

// Shranjevanje seznama v JSON datoteko
fun shraniVDatoteko(context: Context, seznam: List<Student>) {
    val json = Json.encodeToString(seznam)
    context.openFileOutput("students.json", Context.MODE_PRIVATE).use {
        it.write(json.toByteArray())
    }
}

fun preberiIzDatoteke(context: Context): List<Student> {
    return try {
        val vsebina = context.openFileInput("students.json").bufferedReader().readText()
        Json.decodeFromString(vsebina)
    } catch (e: Exception) {
        emptyList() 
    }
}

fun izbrisiDatoteko(context: Context) {
    val file = File(context.filesDir, "students.json")
    if (file.exists()) file.delete()
}

@Preview(showBackground = true)
@Composable
fun Predogled() {
}
