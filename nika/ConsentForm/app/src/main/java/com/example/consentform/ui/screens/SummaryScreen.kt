package com.example.consentform.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.consentform.model.ConsentFormData

@Composable
fun SummaryScreen(
        formData: ConsentFormData,
        onPrevious: () -> Unit,
        onSubmit: () -> Unit,
        isLoading: Boolean,
        modifier: Modifier = Modifier
) {
    Column(
            modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
                text = "Review Your Information",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                        CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
        ) {
            Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                        text = "Personal Information",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                )

                SummaryRow("First Name", formData.firstName)
                SummaryRow("Last Name", formData.lastName)
                SummaryRow("Email", formData.email)
                SummaryRow("Birthday", formData.birthday)
            }
        }

        Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                        CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
        ) {
            Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                        text = "Consent",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                )

                SummaryRow("Agreement", if (formData.hasAgreed) "Agreed" else "Not Agreed")
                SummaryRow(
                        "Signature",
                        if (formData.signature.isNotBlank()) "Provided" else "Not Provided"
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                    onClick = onPrevious,
                    modifier = Modifier.weight(1f),
                    enabled = !isLoading
            ) { Text("Previous") }

            Button(onClick = onSubmit, modifier = Modifier.weight(1f), enabled = !isLoading) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                } else {
                    Text("Submit")
                }
            }
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
                text = "$label:",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
        )
        Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
        )
    }
}
