package ui.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.vaja06naloga01kosarica.model.Artikel
import kotlinx.serialization.json.Json
import java.io.File

@Composable
fun HistoryScreen() {
    val context = LocalContext.current
    val files = remember { getSavedLists(context) }
    var izbranaVsebina by remember { mutableStateOf<List<Artikel>>(emptyList()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Zgodovina shranjenih seznamov", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Seznam datotek
        files.forEach { file ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val vsebina = file.readText()
                    izbranaVsebina = try {
                        Json.decodeFromString(vsebina)
                    } catch (_: Exception) {
                        emptyList()
                    }
                }
                .padding(vertical = 4.dp)) {
                Text("- ${file.name}")
            }
        }

        // Prikaz vsebine izbrane datoteke
        if (izbranaVsebina.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Vsebina izbrane datoteke:", style = MaterialTheme.typography.titleMedium)
            izbranaVsebina.forEach { artikel ->
                Text("• ${artikel.ime} ${if (artikel.vKosarici) "(v košarici)" else ""}")
            }
        }
    }
}

fun getSavedLists(context: Context): List<File> {
    val dir = context.filesDir
    return dir.listFiles { file -> file.name.endsWith(".json") }?.toList() ?: emptyList()
}