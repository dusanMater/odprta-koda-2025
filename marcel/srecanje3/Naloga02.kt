package com.example.vaja03naloga02kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KalkulatorRadio()
        }
    }
}

@Composable
fun KalkulatorRadio() {
    var st1 by remember { mutableStateOf("") }
    var st2 by remember { mutableStateOf("") }

    // Privzet operator pri zagonu je "+"
    var izbranaOperacija by remember { mutableStateOf("+") }

    // Shranimo rezultat izračuna
    var rezultat by remember { mutableStateOf("") }

    // Seznam možnih operacij
    val operacije = listOf("+", "-", "*", "/", "%")

    // Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Polje za prvo število
        OutlinedTextField(
            value = st1,
            onValueChange = { st1 = it },
            label = { Text("Vnesi prvo število") }
        )

        // Polje za drugo število
        OutlinedTextField(
            value = st2,
            onValueChange = { st2 = it },
            label = { Text("Vnesi drugo število") }
        )

        Text("Izberi operacijo:")

        // Radio gumbi za operacije
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            operacije.forEach { operacija ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = izbranaOperacija == operacija,
                        onClick = { izbranaOperacija = operacija }
                    )
                    Text(operacija)
                }
            }
        }

        // Gumb "Izračunaj"
        Button(onClick = {
            rezultat = izracun(st1, st2, izbranaOperacija)
        }) {
            Text("Izračunaj")
        }

        Text("Rezultat: $rezultat", style = MaterialTheme.typography.titleMedium)
    }
}

fun izracun(a: String, b: String, op: String): String {
    // Oba vnosa iz String v Double
    val n1 = a.toDoubleOrNull()
    val n2 = b.toDoubleOrNull()

    // Če vnos ni številka – javi napako
    if (n1 == null || n2 == null) return "Napaka"

    // Glede na operator izvede operacijo
    val rezultat = when (op) {
        "+" -> n1 + n2
        "-" -> n1 - n2
        "*" -> n1 * n2
        "/" -> if (n2 != 0.0) n1 / n2 else return "Deljenje z 0 ni dovoljeno"
        "%" -> if (n2 != 0.0) n1 % n2 else return "Modulus z 0 ni dovoljen"
        else -> return "Neveljaven operator"
    }

    // Rezultat izpiše na 2 decimalki
    return String.format(Locale.US, "%.2f", rezultat)
}


@Preview(showBackground = true)
@Composable
fun Predogled() {
    KalkulatorRadio()
}