package com.example.vaja04nal02seznamstudentov

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

data class Student(val ime: String, val priimek: String, val spol: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StudentApp() }
    }
}

@Composable
fun StudentApp() {
    var ime by remember { mutableStateOf("") }
    var priimek by remember { mutableStateOf("") }
    var spol by remember { mutableStateOf("M") }
    val seznam = remember { mutableStateListOf<Student>() }
    var izbranIndex by remember { mutableIntStateOf(-1) }
    var zadnjiKlik by remember { mutableLongStateOf(0L) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = ime,
            onValueChange = { ime = it },
            label = { Text("Ime") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = priimek,
            onValueChange = { priimek = it },
            label = { Text("Priimek") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Row {
                RadioButton(selected = spol == "M", onClick = { spol = "M" })
                Text("Moški")
            }
            Row {
                RadioButton(selected = spol == "Ž", onClick = { spol = "Ž" })
                Text("Ženska")
            }
        }

        if (izbranIndex == -1) {
            Button(
                onClick = {
                    if (ime.isNotBlank() && priimek.isNotBlank()) {
                        seznam.add(Student(ime, priimek, spol))
                        ime = ""
                        priimek = ""
                        spol = "M"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Dodaj študenta")
            }
        } else {
            Button(
                onClick = {
                    if (ime.isNotBlank() && priimek.isNotBlank()) {
                        seznam[izbranIndex] = Student(ime, priimek, spol)
                        izbranIndex = -1
                        ime = ""
                        priimek = ""
                        spol = "M"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Posodobi študenta")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(seznam) { index, student ->
                Text(
                    text = "${student.ime} ${student.priimek} (${student.spol})",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val trenutniCas = System.currentTimeMillis()

                            // Dvojni klik - izbriše
                            if (izbranIndex == index && trenutniCas - zadnjiKlik < 500) {
                                seznam.removeAt(index)
                                ime = ""
                                priimek = ""
                                spol = "M"
                                izbranIndex = -1
                            } else {
                                // En klik - napolni polja in omogoči posodobitev
                                izbranIndex = index
                                ime = student.ime
                                priimek = student.priimek
                                spol = student.spol
                                zadnjiKlik = trenutniCas
                            }
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PredogledStudentApp() {
    StudentApp()
}