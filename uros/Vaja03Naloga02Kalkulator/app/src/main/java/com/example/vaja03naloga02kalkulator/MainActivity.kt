package com.example.vaja03naloga02kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            kalkulator()
        }
    }
}


@Composable
fun kalkulator(){
    var st1 by remember { mutableStateOf("") }
    var st2 by remember { mutableStateOf("") }
    var vsota by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(/*start = 20.dp,*/top = 20.dp),
        verticalArrangement = Arrangement.Center, // centers children vertically
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row {
            OutlinedTextField(
                value = st1,
                onValueChange = { st1 = it},
                label = { Text("vnesi število") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        Row {
            OutlinedTextField(
                value = st2,
                onValueChange = { st2 = it},
                label = { Text("vnesi število") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        Row {
            Column {
                Button(onClick = {vsota = izracunaj (st1, st2, "+")}) {
                    Text("+")
                }
            }
            Column {
                Button(onClick = {vsota = izracunaj (st1, st2, "-")}) {
                    Text("-")
                }
            }
            Column {
                Button(onClick = {vsota = izracunaj (st1, st2, "*")}) {
                    Text("*")
                }
            }
            Column {
                Button(onClick = {vsota = izracunaj (st1, st2, "/")}) {
                    Text("/")
                }
            }
            Column {
                Button(onClick = {vsota = izracunaj (st1, st2, "%")}) {
                    Text("%")
                }
            }

        }
        Row {
            Text("rezultat =" + vsota)
        }
    }
}

fun izracunaj (prvo: String, drugo: String, operator: String): String{
    var vsota: Any = ""
    if( operator == "+"){
        vsota = (prvo.toDouble() + drugo.toDouble())
    }
    else if(operator == "-"){
        vsota = (prvo.toDouble() - drugo.toDouble())
    }
    else if(operator == "*"){
        vsota = (prvo.toDouble() * drugo.toDouble())
    }
    else if(operator == "/"){
        vsota = (prvo.toDouble() / drugo.toDouble())
    }
    else if(operator == "%"){
        vsota = (prvo.toDouble() % drugo.toDouble())
    }
    return vsota.toString()
}