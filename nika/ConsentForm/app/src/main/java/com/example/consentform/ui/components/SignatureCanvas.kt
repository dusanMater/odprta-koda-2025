package com.example.consentform.ui.components

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Base64
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import java.io.ByteArrayOutputStream

@Composable
fun SignatureCanvas(onSignatureChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    var paths by remember { mutableStateOf(listOf<List<Offset>>()) }
    var currentPath by remember { mutableStateOf(listOf<Offset>()) }

    Column(modifier = modifier) {
        Box(
                modifier =
                        Modifier.fillMaxWidth()
                                .height(200.dp)
                                .border(1.dp, MaterialTheme.colorScheme.outline)
                                .background(Color.White)
        ) {
            Canvas(
                    modifier =
                            Modifier.fillMaxSize().clipToBounds().pointerInput(Unit) {
                                detectDragGestures(
                                        onDragStart = { offset -> currentPath = listOf(offset) },
                                        onDragEnd = {
                                            if (currentPath.isNotEmpty()) {
                                                paths = paths + listOf(currentPath)
                                                currentPath = emptyList()

                                                // Convert to base64
                                                val base64 =
                                                        pathsToBase64(
                                                                paths + listOf(currentPath),
                                                                size.width,
                                                                size.height
                                                        )
                                                onSignatureChanged(base64)
                                            }
                                        },
                                        onDrag = { change, _ ->
                                            currentPath = currentPath + change.position
                                        }
                                )
                            }
            ) {
                val strokeWidth = 3.dp.toPx()

                // Draw all completed paths
                paths.forEach { path ->
                    if (path.size > 1) {
                        for (i in 0 until path.size - 1) {
                            drawLine(
                                    color = Color.Black,
                                    start = path[i],
                                    end = path[i + 1],
                                    strokeWidth = strokeWidth,
                                    cap = StrokeCap.Round
                            )
                        }
                    }
                }

                // Draw current path being drawn
                if (currentPath.size > 1) {
                    for (i in 0 until currentPath.size - 1) {
                        drawLine(
                                color = Color.Black,
                                start = currentPath[i],
                                end = currentPath[i + 1],
                                strokeWidth = strokeWidth,
                                cap = StrokeCap.Round
                        )
                    }
                }
            }

            if (paths.isEmpty() && currentPath.isEmpty()) {
                Text(
                        text = "Sign here",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
                onClick = {
                    paths = emptyList()
                    currentPath = emptyList()
                    onSignatureChanged("")
                },
                modifier = Modifier.align(Alignment.End)
        ) { Text("Clear") }
    }
}

private fun pathsToBase64(paths: List<List<Offset>>, width: Int, height: Int): String {
    if (paths.all { it.isEmpty() }) return ""

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint =
            Paint().apply {
                color = android.graphics.Color.BLACK
                strokeWidth = 6f
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                isAntiAlias = true
            }

    canvas.drawColor(android.graphics.Color.WHITE)

    paths.forEach { path ->
        if (path.size > 1) {
            for (i in 0 until path.size - 1) {
                canvas.drawLine(path[i].x, path[i].y, path[i + 1].x, path[i + 1].y, paint)
            }
        }
    }

    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}
