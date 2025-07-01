package com.example.vaja03naloga03seznamcdjev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CDIzpis()
        }
    }
}

@Composable
fun CDIzpis(){
    val seznamCDjev = listOf(
        CD("Metalica", "Metalica", R.drawable.album1),
        CD("Bruce Springsteen", "The Dark Side of the Moon", R.drawable.album2),
        CD("Madonna", "Madonna", R.drawable.album3)
    )

    var id = 0
    Column (
        Modifier.padding((10.dp))
    ){
        Text("seznamCdjev", Modifier.padding(bottom = 10.dp))
        seznamCDjev.forEach() {cd -> VrsticaIzris(cd) }
    }
}

@Composable
fun VrsticaIzris(cd:CD){
    Row (Modifier.padding(bottom = 10.dp)) {
        Image(
            painter = painterResource(cd.slika),
            contentDescription = "Na sliki je " + cd.Izvajalec + " " + cd.Album,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(cd.Izvajalec, fontSize = 20.sp)
            Text(cd.Album)
        }
    }
}

