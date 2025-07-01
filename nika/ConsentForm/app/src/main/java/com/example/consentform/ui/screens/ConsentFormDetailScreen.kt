package com.example.consentform.ui.screens

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.consentform.model.ConsentFormData
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsentFormDetailScreen(
        consentForm: ConsentFormData,
        onBack: () -> Unit,
        onUpdateNames: ((String, String) -> Unit)? = null, // Callback for saving updated names
        modifier: Modifier = Modifier
) {
        // State for editing mode
        var isEditingFirstName by remember { mutableStateOf(false) }
        var isEditingLastName by remember { mutableStateOf(false) }

        // State for edited values
        var editedFirstName by remember { mutableStateOf(consentForm.firstName) }
        var editedLastName by remember { mutableStateOf(consentForm.lastName) }

        Column(modifier = modifier.fillMaxSize()) {
                // Top App Bar with back button
                TopAppBar(
                        title = { Text("Consent Form Details") },
                        navigationIcon = {
                                IconButton(onClick = onBack) {
                                        Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = "Back"
                                        )
                                }
                        }
                )

                // Scrollable content
                Column(
                        modifier =
                                Modifier.fillMaxSize()
                                        .verticalScroll(rememberScrollState())
                                        .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                        // Personal Information Card
                        Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors =
                                        CardDefaults.cardColors(
                                                containerColor =
                                                        MaterialTheme.colorScheme.primaryContainer
                                        )
                        ) {
                                Column(
                                        modifier = Modifier.padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                        Text(
                                                text = "Personal Information",
                                                style = MaterialTheme.typography.headlineSmall,
                                                fontWeight = FontWeight.Bold
                                        )

                                        // Editable First Name
                                        EditableDetailRow(
                                                label = "First Name",
                                                value = consentForm.firstName,
                                                editedValue = editedFirstName,
                                                isEditing = isEditingFirstName,
                                                onEditClick = { isEditingFirstName = true },
                                                onValueChange = { editedFirstName = it },
                                                onSave = {
                                                        isEditingFirstName = false
                                                        onUpdateNames?.invoke(
                                                                editedFirstName,
                                                                editedLastName
                                                        )
                                                },
                                                onCancel = {
                                                        isEditingFirstName = false
                                                        editedFirstName = consentForm.firstName
                                                }
                                        )

                                        // Editable Last Name
                                        EditableDetailRow(
                                                label = "Last Name",
                                                value = consentForm.lastName,
                                                editedValue = editedLastName,
                                                isEditing = isEditingLastName,
                                                onEditClick = { isEditingLastName = true },
                                                onValueChange = { editedLastName = it },
                                                onSave = {
                                                        isEditingLastName = false
                                                        onUpdateNames?.invoke(
                                                                editedFirstName,
                                                                editedLastName
                                                        )
                                                },
                                                onCancel = {
                                                        isEditingLastName = false
                                                        editedLastName = consentForm.lastName
                                                }
                                        )

                                        DetailRow("Email", consentForm.email)
                                        DetailRow(
                                                "Birthday",
                                                formatDateToDDMMYYYY(consentForm.birthday)
                                        )
                                        DetailRow(
                                                "Submitted",
                                                formatDateTimeToDDMMYYYY(consentForm.submittedAt)
                                        )
                                }
                        }

                        // Signature Card
                        Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors =
                                        CardDefaults.cardColors(
                                                containerColor =
                                                        MaterialTheme.colorScheme.secondaryContainer
                                        )
                        ) {
                                Column(
                                        modifier = Modifier.padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                        Text(
                                                text = "Digital Signature",
                                                style = MaterialTheme.typography.headlineSmall,
                                                fontWeight = FontWeight.Bold
                                        )

                                        if (consentForm.signature.isNotEmpty()) {
                                                val signatureBitmap =
                                                        remember(consentForm.signature) {
                                                                try {
                                                                        // Clean the base64 string
                                                                        // (remove any
                                                                        // whitespace/newlines)
                                                                        val cleanedSignature =
                                                                                consentForm
                                                                                        .signature
                                                                                        .trim()
                                                                                        .replace(
                                                                                                "\\s".toRegex(),
                                                                                                ""
                                                                                        )

                                                                        if (cleanedSignature
                                                                                        .isEmpty()
                                                                        ) {
                                                                                println(
                                                                                        "Empty signature after cleaning"
                                                                                )
                                                                                return@remember null
                                                                        }

                                                                        val imageBytes =
                                                                                Base64.decode(
                                                                                        cleanedSignature,
                                                                                        Base64.DEFAULT
                                                                                )
                                                                        val bitmap =
                                                                                BitmapFactory
                                                                                        .decodeByteArray(
                                                                                                imageBytes,
                                                                                                0,
                                                                                                imageBytes
                                                                                                        .size
                                                                                        )

                                                                        if (bitmap == null) {
                                                                                println(
                                                                                        "BitmapFactory.decodeByteArray returned null"
                                                                                )
                                                                                println(
                                                                                        "Image bytes length: ${imageBytes.size}"
                                                                                )
                                                                        }

                                                                        bitmap
                                                                } catch (e: Exception) {
                                                                        println(
                                                                                "Signature decoding error: ${e.message}"
                                                                        )
                                                                        println(
                                                                                "Signature length: ${consentForm.signature.length}"
                                                                        )
                                                                        println(
                                                                                "Signature starts with: ${consentForm.signature.take(50)}"
                                                                        )
                                                                        null
                                                                }
                                                        }

                                                if (signatureBitmap != null) {
                                                        Card(
                                                                modifier =
                                                                        Modifier.fillMaxWidth()
                                                                                .height(200.dp),
                                                                colors =
                                                                        CardDefaults.cardColors(
                                                                                containerColor =
                                                                                        MaterialTheme
                                                                                                .colorScheme
                                                                                                .surface
                                                                        )
                                                        ) {
                                                                Box(
                                                                        modifier =
                                                                                Modifier.fillMaxSize()
                                                                                        .padding(
                                                                                                8.dp
                                                                                        ),
                                                                        contentAlignment =
                                                                                Alignment.Center
                                                                ) {
                                                                        Image(
                                                                                bitmap =
                                                                                        signatureBitmap
                                                                                                .asImageBitmap(),
                                                                                contentDescription =
                                                                                        "Digital Signature",
                                                                                modifier =
                                                                                        Modifier.fillMaxSize()
                                                                        )
                                                                }
                                                        }
                                                } else {
                                                        Column {
                                                                Text(
                                                                        text =
                                                                                "Error: Unable to decode signature",
                                                                        color =
                                                                                MaterialTheme
                                                                                        .colorScheme
                                                                                        .error
                                                                )
                                                                Text(
                                                                        text =
                                                                                "Signature length: ${consentForm.signature.length} characters",
                                                                        style =
                                                                                MaterialTheme
                                                                                        .typography
                                                                                        .bodySmall,
                                                                        color =
                                                                                MaterialTheme
                                                                                        .colorScheme
                                                                                        .onSurfaceVariant
                                                                )
                                                                if (consentForm.signature.length > 0
                                                                ) {
                                                                        Text(
                                                                                text =
                                                                                        "Preview: ${consentForm.signature.take(100)}...",
                                                                                style =
                                                                                        MaterialTheme
                                                                                                .typography
                                                                                                .bodySmall,
                                                                                color =
                                                                                        MaterialTheme
                                                                                                .colorScheme
                                                                                                .onSurfaceVariant
                                                                        )
                                                                }
                                                        }
                                                }
                                        } else {
                                                Text(
                                                        text = "No signature available",
                                                        color =
                                                                MaterialTheme.colorScheme
                                                                        .onSurfaceVariant
                                                )
                                        }
                                }
                        }

                        // Consent Agreement Card
                        Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors =
                                        CardDefaults.cardColors(
                                                containerColor =
                                                        MaterialTheme.colorScheme.tertiaryContainer
                                        )
                        ) {
                                Column(
                                        modifier = Modifier.padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                        Text(
                                                text = "Consent Agreement",
                                                style = MaterialTheme.typography.headlineSmall,
                                                fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                                text =
                                                        if (consentForm.hasAgreed)
                                                                "✓ The participant has agreed to the consent form terms and conditions."
                                                        else
                                                                "✗ The participant has not agreed to the consent form.",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color =
                                                        if (consentForm.hasAgreed)
                                                                MaterialTheme.colorScheme.primary
                                                        else MaterialTheme.colorScheme.error
                                        )
                                }
                        }
                }
        }
}

// Helper function to format birthday (YYYY-MM-DD) to dd.mm.YYYY
private fun formatDateToDDMMYYYY(dateString: String): String {
        if (dateString.isBlank()) return "N/A"

        return try {
                // Parse YYYY-MM-DD format
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val date = inputFormat.parse(dateString)
                date?.let { outputFormat.format(it) } ?: dateString
        } catch (e: Exception) {
                dateString // Return original if parsing fails
        }
}

// Helper function to format submission datetime to dd.mm.YYYY
private fun formatDateTimeToDDMMYYYY(dateTimeString: String?): String {
        if (dateTimeString.isNullOrBlank()) return "N/A"

        return try {
                // Parse various possible datetime formats
                val possibleFormats =
                        listOf(
                                "yyyy-MM-dd HH:mm:ss",
                                "yyyy-MM-dd'T'HH:mm:ss",
                                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                                "yyyy-MM-dd"
                        )

                val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

                for (formatPattern in possibleFormats) {
                        try {
                                val inputFormat =
                                        SimpleDateFormat(formatPattern, Locale.getDefault())
                                val date = inputFormat.parse(dateTimeString)
                                return date?.let { outputFormat.format(it) } ?: dateTimeString
                        } catch (e: Exception) {
                                // Try next format
                        }
                }

                // If no format works, return original
                dateTimeString
        } catch (e: Exception) {
                dateTimeString
        }
}

@Composable
private fun DetailRow(label: String, value: String, modifier: Modifier = Modifier) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                        text = "$label:",
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                )
                Text(text = value, modifier = Modifier.weight(2f))
        }
}

@Composable
private fun EditableDetailRow(
        label: String,
        value: String,
        editedValue: String,
        isEditing: Boolean,
        onEditClick: () -> Unit,
        onValueChange: (String) -> Unit,
        onSave: () -> Unit,
        onCancel: () -> Unit
) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                        text = "$label:",
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                )
                if (isEditing) {
                        TextField(
                                value = editedValue,
                                onValueChange = onValueChange,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                modifier = Modifier.weight(2f)
                        )
                        IconButton(onClick = onSave) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
                        }
                        IconButton(onClick = onCancel) {
                                Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Cancel"
                                )
                        }
                } else {
                        Text(text = value, modifier = Modifier.weight(2f))
                        IconButton(onClick = onEditClick) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        }
                }
        }
}
