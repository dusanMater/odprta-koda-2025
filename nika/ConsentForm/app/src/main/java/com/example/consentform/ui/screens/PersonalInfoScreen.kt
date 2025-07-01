package com.example.consentform.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.consentform.R
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoScreen(
        firstName: String,
        lastName: String,
        email: String,
        birthday: String,
        onFirstNameChange: (String) -> Unit,
        onLastNameChange: (String) -> Unit,
        onEmailChange: (String) -> Unit,
        onBirthdayChange: (String) -> Unit,
        onNext: () -> Unit,
        isNextEnabled: Boolean,
        modifier: Modifier = Modifier
) {
        // Convert backend format (YYYY-MM-DD) to display format (dd.mm.yyyy) for the text field
        val displayBirthday = remember(birthday) { convertYYYYMMDDtoDDMMYYYY(birthday) }

        // Store the display format internally
        var birthdayDisplayValue by remember(birthday) { mutableStateOf(displayBirthday) }

        Column(
                modifier =
                        modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                // Logo Section
                Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors =
                                CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                )
                ) {
                        Column(
                                modifier =
                                        Modifier.fillMaxWidth()
                                                .padding(vertical = 16.dp, horizontal = 0.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                                Image(
                                        painter =
                                                painterResource(
                                                        id =
                                                                R.drawable
                                                                        .spiral_ink_logo_black_transparent
                                                ),
                                        contentDescription = "Spiral Ink Tattoo Studio",
                                        modifier = Modifier.fillMaxWidth()
                                )
                        }
                }

                Text(
                        text = "Personal Information",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onSurface
                )

                OutlinedTextField(
                        value = firstName,
                        onValueChange = onFirstNameChange,
                        label = { Text("First Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                )

                OutlinedTextField(
                        value = lastName,
                        onValueChange = onLastNameChange,
                        label = { Text("Last Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                )

                OutlinedTextField(
                        value = email,
                        onValueChange = onEmailChange,
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                OutlinedTextField(
                        value = birthdayDisplayValue,
                        onValueChange = { newValue ->
                                // Update display value
                                birthdayDisplayValue = newValue

                                // Convert to backend format and notify parent
                                val backendFormat = convertDDMMYYYYtoYYYYMMDD(newValue)
                                onBirthdayChange(backendFormat)
                        },
                        label = { Text("Birthday (dd.mm.yyyy)") },
                        placeholder = { Text("15.01.1990") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        supportingText = { Text("Enter your birth date in day.month.year format") }
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                        onClick = onNext,
                        enabled = isNextEnabled,
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) { Text("Next") }
        }
}

// Convert backend format (YYYY-MM-DD) to display format (dd.mm.yyyy)
private fun convertYYYYMMDDtoDDMMYYYY(backendDate: String): String {
        if (backendDate.isBlank()) return ""

        return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val date = inputFormat.parse(backendDate)
                date?.let { outputFormat.format(it) } ?: ""
        } catch (e: Exception) {
                // If parsing fails, try to handle it gracefully
                backendDate
        }
}

// Convert display format (dd.mm.yyyy) to backend format (YYYY-MM-DD)
private fun convertDDMMYYYYtoYYYYMMDD(displayDate: String): String {
        if (displayDate.isBlank()) return ""

        return try {
                // Handle partial input gracefully
                if (displayDate.length < 10) return ""

                val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = inputFormat.parse(displayDate)
                date?.let { outputFormat.format(it) } ?: ""
        } catch (e: Exception) {
                // Return empty string for invalid dates
                ""
        }
}
