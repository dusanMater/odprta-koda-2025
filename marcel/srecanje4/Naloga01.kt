package com.example.vaja04nal01vreme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.statusBarsPadding

data class VremeUra(
    val ura: String,
    val temperatura: Int,
    val vremeIkona: Int,
    val smerVetra: String,
    val hitrostVetra: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VremenskaNapoved()
        }
    }
}

@Composable
fun VremenskaNapoved() {
    val napoved = listOf(
        VremeUra("14:00", 22, R.drawable.soncno, "↗", 10),
        VremeUra("15:00", 15, R.drawable.oblacno, "↘", 22),
        VremeUra("16:00", 14, R.drawable.oblacno, "↗", 10),
        VremeUra("17:00", 12, R.drawable.dezevno, "↗", 17),
        VremeUra("18:00", 11, R.drawable.dezevno, "↘", 9)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize() // zapolni celoten zaslon
            .statusBarsPadding() // doda razmak na vrhu da ni pod kamero ali status barom
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // razmik ved vrsticami
    ) {
        items(napoved) { vreme ->
            VrsticaVremena(vreme)
        }
    }
}

@Composable
fun VrsticaVremena(podatek: VremeUra) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = podatek.ura, fontSize = 16.sp)

        // Izpiše temperaturo + doda °C
        Text(text = "${podatek.temperatura} °C", fontSize = 16.sp)

        Image(
            painter = painterResource(id = podatek.vremeIkona),
            contentDescription = "vreme",
            modifier = Modifier.size(32.dp)
        )

        Text(text = "${podatek.smerVetra} ${podatek.hitrostVetra} km/h", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PredogledVremena() {
    VremenskaNapoved()
}