package com.example.predavanje04naloga2_seznamtudentov

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.predavanje04naloga2_seznamtudentov.ui.theme.Predavanje04Naloga2seznamŠtudentovTheme
import com.example.predavanje04naloga2_seznamtudentov.Student
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun Main() {

    val context = LocalContext.current

    // lokalne spremenljivke
    var ime by remember { mutableStateOf("") }
    var priimek by remember { mutableStateOf("") }
    var spol by remember { mutableStateOf("") }
    var stanje by remember { mutableStateOf("") }
    var id by remember { mutableStateOf(1) }

    var studenti = remember {mutableStateListOf<Student>()}

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    )
    {
        OutlinedTextField(
            value = ime,
            onValueChange = { ime = it},
            label = {Text("Ime")}
        )

        OutlinedTextField(
            value = priimek,
            onValueChange = { priimek = it},
            label = {Text("Priimek")}
        )

        Row()
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start)
            {
                RadioButton(
                    selected = spol == "moski",
                    onClick = {spol = "moski"}
                )
                Text("Moski")
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start)
            {
                RadioButton(
                    selected = spol == "zenska",
                    onClick = {spol = "zenska"}
                )
                Text("Zenska")
            }

        }

        Button(
            onClick = {


                if(stanje == "P") {
                    stanje = "D"
                    val i = studenti.indexOfFirst { it.id == id }
                    Toast.makeText(context, "Hello, World : ${i}", Toast.LENGTH_SHORT).show()

                    if (i > -1)
                        studenti[i] = studenti[i].copy(ime = ime, priimek = priimek, spol = spol)
                }
                   else
                {studenti.add(Student(id,ime,priimek,spol))
                    id++

                    }
                ime = ""
                priimek = ""
                spol = ""
            }
        ) {
            var tekst = ""
            if(stanje == "D")
                tekst = "Dodaj"
            else
                tekst = "Posodobi"
            Text("$tekst študenta")
    }

        LazyColumn {
            items(studenti) {student ->
                Column(
                    modifier = Modifier
                        .background(Color.Cyan).fillMaxWidth()
                        .border(10.dp, Color.Black)
                        .combinedClickable(
                            onClick = {
                                ime = student.ime
                                priimek = student.priimek
                                spol = student.spol
                                stanje = "P"
                                // najdi id iskanega
                                id = student.id
                            },
                            onLongClick = {
                                studenti.remove(student)
                            }
                        )
                        )

                {
                    Text(

                        text = "ime = ${student.ime}, priimek = ${student.priimek}, ID = ${student.id}",
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }
        }

}


}
