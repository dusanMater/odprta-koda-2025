package com.example.vaja03naloga01kalkulatordemo

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
            Kalkulator()
        }
    }
}

@Composable
fun Kalkulator() {
    // Shranjevanje prvega vnosa
    var st1 by remember { mutableStateOf("") }

    // Shranjevanje drugega vnosa
    var st2 by remember { mutableStateOf("") }

    // Rezultat operacije (prikaže se spodaj)
    var rezultat by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()       // zavzame celoten zaslon
            .padding(24.dp),     // rob okoli vsebine
        horizontalAlignment = Alignment.CenterHorizontally,  // sredinska poravnava
        verticalArrangement = Arrangement.spacedBy(16.dp)     // razmik med elementi
    ) {
        // Vnosno polje za prvo število
        OutlinedTextField(
            value = st1,
            onValueChange = { st1 = it },
            label = { Text("Vnesi prvo število") }
        )

        // Vnosno polje za drugo število
        OutlinedTextField(
            value = st2,
            onValueChange = { st2 = it },
            label = { Text("Vnesi drugo število") }
        )

        // Vrstica z gumbi
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { rezultat = izracun(st1, st2, "+") }) { Text("+") }
            Button(onClick = { rezultat = izracun(st1, st2, "-") }) { Text("-") }
            Button(onClick = { rezultat = izracun(st1, st2, "*") }) { Text("*") }
            Button(onClick = { rezultat = izracun(st1, st2, "/") }) { Text("/") }
            Button(onClick = { rezultat = izracun(st1, st2, "%") }) { Text("%") }
        }

        Text(
            text = "Rezultat: $rezultat",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

// Funkcija za izračun na podlagi izbranega operatorja
fun izracun(a: String, b: String, op: String): String {
    // Vneseni številki pretvori iz besedila v število
    val n1 = a.toDoubleOrNull()
    val n2 = b.toDoubleOrNull()

    // Če vnos ni število, javi napako
    if (n1 == null || n2 == null) return "Napaka"

    // Rezultat izračuna glede na izbran operator
    val rezultat = when (op) {
        "+" -> n1 + n2
        "-" -> n1 - n2
        "*" -> n1 * n2
        "/" -> if (n2 != 0.0) n1 / n2 else return "Deljenje z 0 ni dovoljeno"
        "%" -> if (n2 != 0.0) n1 % n2 else return "Modulus z 0 ni dovoljen"
        else -> return "Neveljaven operator"
    }

    // Vrne rezultat zaokrožen na 2 decimalki
    return String.format(Locale.US, "%.2f", rezultat)
}

@Preview(showBackground = true)
@Composable
fun Predogled() {
    Kalkulator()
}