package com.example.vaja03naloga03cddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class CD(val izvajalec: String, val naslov: String, val slikaResId: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CDSeznam()
        }
    }
}

@Composable
fun CDSeznam() {
    // Seznam CD-jev
    val cdji = listOf(
        CD("Linkin Park", "Hybrid Theory", R.drawable.album1),
        CD("Adele", "21", R.drawable.album2),
        CD("Daft Punk", "Random Access Memories", R.drawable.album3)
    )

    // LazyColumn za prikaz seznama
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cdji) { cd ->
            CDKartica(cd)
        }
    }
}

// Prikaz posameznega CD-ja
@Composable
fun CDKartica(cd: CD) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = cd.slikaResId),
                contentDescription = "Slika albuma",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Izvajalec: ${cd.izvajalec}", style = MaterialTheme.typography.titleMedium)
                Text("Album: ${cd.naslov}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PredogledCD() {
    CDSeznam()
}