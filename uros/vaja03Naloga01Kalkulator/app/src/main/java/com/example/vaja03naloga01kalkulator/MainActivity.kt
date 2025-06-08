package com.example.vaja03naloga01kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
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
    var operator by remember { mutableStateOf("") }
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
        Row(modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)) {
            Text("izberi Operacijo:")
        }
        Row {
            Column {
                RadioButton(
                    selected = operator == "-",
                    onClick = { operator = "-" },
                )
            }
            Column {
                Text("-", modifier = Modifier.padding(top = 15.dp, end = 10.dp))
            }
            Column {
                Box(
                    modifier = Modifier
                        .width(1.dp)                // thickness of the line
                        .height(40.dp)             // line spans full height of parent
                        .background(Color.Gray)     // line color
                )
            }
            Column {
                RadioButton(
                    selected = operator == "+",
                    onClick = { operator = "+" }
                )
            }
            Column {
                Text("+", modifier = Modifier.padding(top = 15.dp, end = 10.dp))
            }
            Column {
                Box(
                    modifier = Modifier
                        .width(1.dp)                // thickness of the line
                        .height(40.dp)            // line spans full height of parent
                        .background(Color.Gray)     // line color
                )
            }
            Column {
                RadioButton(
                    selected = operator == "*",
                    onClick = { operator = "*" }
                )
            }
            Column {
                Text("*", modifier = Modifier.padding(top = 15.dp, end = 10.dp))
            }
            Column {
                Box(
                    modifier = Modifier
                        .width(1.dp)                // thickness of the line
                        .height(40.dp)           // line spans full height of parent
                        .background(Color.Gray)     // line color
                )
            }
            Column {
                RadioButton(
                    selected = operator == "/",
                    onClick = { operator = "/" }
                )
            }
            Column {
                Text("/", modifier = Modifier.padding(top = 15.dp, end = 10.dp))
            }
            Column {
                Box(
                    modifier = Modifier
                        .width(1.dp)                // thickness of the line
                        .height(40.dp)            // line spans full height of parent
                        .background(Color.Gray)     // line color
                )
            }
            Column {
                RadioButton(
                    selected = operator == "%",
                    onClick = { operator = "%" }
                )
            }
            Column {
                Text("%", modifier = Modifier.padding(top = 15.dp))
            }


        }
        Row {
            Button(onClick = {vsota = izracunaj (st1, st2, operator)}) {
                Text("izračunaj")
            }
        }
        Row {
            Text("rezultat =" + vsota)
        }
    }
}

fun izracunaj (prvo: String, drugo: String, operator: String): String{
    var vsota: Any = ""
    if(operator == null || prvo == null || drugo == null) {
        println("midding fun element: " + " prvo: " +prvo + " drugo: " + drugo + " operator " + operator)
        return ""
    }
    if (operator == "+") {
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