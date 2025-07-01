package com.example.vaja_student

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            studentiApp()
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "student_prefs")


@Composable
fun studentiApp() {
    val context = LocalContext.current

    var ime by remember { mutableStateOf("") }
    var priimek by remember { mutableStateOf("") }
    var spol by remember { mutableStateOf("") }
    var students = remember { mutableStateListOf<studentData>() }

    var showSaveDialog by remember { mutableStateOf(false) }
    var showLoadDialog by remember { mutableStateOf(false) }
    var fileNameInput by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val loadedStudents = loadStudents(context)
        students.addAll(loadedStudents)
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(/*start = 20.dp,*/top = 20.dp),
        verticalArrangement = Arrangement.Center, // centers children vertically
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row {
            OutlinedTextField(
                value = ime,
                onValueChange = { ime = it},
                label = { Text("vnesi ime") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        Row {
            OutlinedTextField(
                value = priimek,
                onValueChange = { priimek = it},
                label = { Text("vnesi priimek") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row(modifier = Modifier
            .fillMaxWidth(0.71f)
            .border(width = 2.dp, color = Color.Gray, shape = RectangleShape)
            .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Column {
                Text("Spol: ")
            }
            Column {
                RadioButton(
                    selected = spol == "Z",
                    onClick = { spol = "Z" }
                )
            }
            Column {
                Text("Z")
            }
            Column {
                RadioButton(
                    selected = spol == "M",
                    onClick = { spol = "M" }
                )
            }
            Column {
                Text("M")
            }
        }
        Row {
            Button(onClick = {
                if(ime != "" && priimek != "" && spol != "") {
                    students.add(studentData(ime, priimek, spol))
                    ime = ""
                    priimek = ""
                    spol = ""
                }
            }) {
                Text("Dodaj studenta")
            }
        }
        Row {
            if (students.isNotEmpty()) {
                // Pass the students list directly
                createList(studentList = students)
            } else {
                Text("Seznam študentov je prazen.") // "Student list is empty."
            }
        }
        Row {
            Column {
                Button(onClick = {
                    showSaveDialog = true
                    /*CoroutineScope(Dispatchers.IO).launch {
                        saveStudents(context, students)
                    }*/
                }) {
                    Text("Shrani")
                }
            }
            Column {
                Button(onClick = {
                    showLoadDialog = true
                    /*CoroutineScope(Dispatchers.IO).launch {
                        val loadedStudents = loadStudents(context)
                        students.clear() // Clear current list before adding
                        students.addAll(loadedStudents)
                    }*/
                }) {
                    Text("Naloži Seznam")
                }
            }
            Column {
                Button(onClick = {
                    students.clear()
                }) {
                    Text("Briši Seznam.")
                }
            }
        }

    }
    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        saveStudents(context, students, fileNameInput.ifBlank { "students.json" })
                    }
                    showSaveDialog = false
                    fileNameInput = ""
                }) {
                    Text("Shrani")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showSaveDialog = false
                    fileNameInput = ""
                }) {
                    Text("Prekliči")
                }
            },
            title = { Text("Shrani v datoteko") },
            text = {
                TextField(
                    value = fileNameInput,
                    onValueChange = { fileNameInput = it },
                    label = { Text("Ime datoteke") },
                    placeholder = { Text("students.json") }
                )
            }
        )
    }

    // Load dialog
    if (showLoadDialog) {
        AlertDialog(
            onDismissRequest = { showLoadDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val loaded = loadStudents(context, fileNameInput.ifBlank { "students.json" })
                        students.clear()
                        students.addAll(loaded)
                    }
                    showLoadDialog = false
                    fileNameInput = ""
                }) {
                    Text("Naloži")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showLoadDialog = false
                    fileNameInput = ""
                }) {
                    Text("Prekliči")
                }
            },
            title = { Text("Naloži iz datoteke") },
            text = {
                TextField(
                    value = fileNameInput,
                    onValueChange = { fileNameInput = it },
                    label = { Text("Ime datoteke") },
                    placeholder = { Text("students.json") }
                )
            }
        )
    }
}

@Composable
fun createList(studentList: List<studentData>) {
    Column(modifier = Modifier
        .fillMaxWidth(0.71f)
        .height(height = 450.dp)
        .border(width = 2.dp, color = Color.Gray, shape = RectangleShape),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        studentList.forEach(){student -> createStudentRow(student)}
    }
}

@Composable
fun createStudentRow(studentData: studentData) {
    var bckgColor = Color.White
    if(studentData.spol == "Z"){
        bckgColor = Color.Red
    }
    else if(studentData.spol == "M"){
        bckgColor = Color.Cyan
    }
    Row(modifier = Modifier
        .background(color = bckgColor)
        .fillMaxWidth()
        .height(height = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text("Ime: " + studentData.ime + "   Priimek: " + studentData.priimet)
    }
}

suspend fun saveStudents(context: Context, students: List<studentData>, fileName: String = "students.json") {
    val gson = Gson()
    val jsonString = gson.toJson(students)

    val file = File(context.filesDir, fileName)
    file.writeText(jsonString)
}

suspend fun loadStudents(context: Context, fileName: String = "students.json"): List<studentData> {
    val file = File(context.filesDir, fileName)
    if (!file.exists()) return emptyList()

    val jsonString = file.readText()
    val type = object : TypeToken<List<studentData>>() {}.type
    return Gson().fromJson(jsonString, type)
}
