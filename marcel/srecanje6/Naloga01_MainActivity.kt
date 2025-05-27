package com.example.vaja06naloga01kosarica

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vaja06naloga01kosarica.model.Artikel
import ui.screens.HistoryScreen
import com.example.vaja06naloga01kosarica.ui.screens.MainScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainScreen(navController) }
                composable("history") { HistoryScreen() }
            }
        }
    }
}

// Shranjevanje seznama v datoteko
fun saveArtikelToFile(context: Context, artikel: List<Artikel>) {
    val jsonString = Json.encodeToString(artikel)

    // Ime datoteke glede na trenutni čas
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "artikel_$timestamp.json"

    val file = File(context.filesDir, fileName)
    file.writeText(jsonString)
}

// Prikaz zadnjega seznama oz. datoteke na glavnem zaslonu
fun loadStudentsListFromFile(context: Context): List<Artikel> {
    return try {
        val zadnja = context.filesDir
            .listFiles { file -> file.name.endsWith(".json") }
            ?.maxByOrNull { it.lastModified() }

        if (zadnja != null) {
            val jsonString = zadnja.readText()
            Json.decodeFromString(jsonString)
        } else {
            emptyList()
        }
    } catch (_: Exception) {
        emptyList()
    }
}