package com.example.predavanje03naloga03_seznamcdjev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje03naloga03_seznamcdjev.ui.theme.Predavanje03Naloga03SeznamCDjevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IzpisCDjev()
        }
    }
}

@Composable
fun IzpisCDjev() {

    val seznamCDjev = listOf(
        CD("Metallica", "Master of Puppets", R.drawable.album1),
        CD("Bruce Springsteen", "The Ties That Bind", R.drawable.album2),
        CD("Madonna", "Madonna", R.drawable.album3)

    )

    Column(
        Modifier.padding(10.dp)
            .padding(top = 20.dp)
    )
    {
        Text("Seznam CDjev", fontSize = 30.sp)

        //izrisiVrstico(seznamCDjev[0])
        //izrisiVrstico(seznamCDjev[1])
        //izrisiVrstico(seznamCDjev[2])

        var i = 0
        while( i < seznamCDjev.size) {
            IzrisiVrstico(seznamCDjev[i])
            i++
        }
    }

}

@Composable
fun IzrisiVrstico(cd:CD) {

    Row(
        Modifier.padding(bottom = 10.dp)
    )
    {
        Image(
            painter = painterResource(cd.slika),
            contentDescription = "Na sliki je " + cd.izvajalec + " " + cd.album,
            Modifier.size(100.dp)
                .padding(end = 10.dp),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(cd.izvajalec, fontSize = 20.sp)
            Text(cd.album)
        }
    }


}