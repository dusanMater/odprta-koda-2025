package com.example.consentform.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.consentform.model.ConsentFormData
import com.example.consentform.viewmodel.AdminUiState
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
        adminState: AdminUiState,
        onLoadForms: (page: Int, search: String?, sortBy: String?, sortOrder: String?) -> Unit,
        onDeleteForm: (Long) -> Unit,
        onSearchQueryChange: (String) -> Unit,
        onFormClick: (ConsentFormData) -> Unit,
        modifier: Modifier = Modifier
) {
    var showDeleteDialog by remember { mutableStateOf<ConsentFormData?>(null) }

    LaunchedEffect(Unit) { onLoadForms(1, null, null, null) }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        // Search Bar
        OutlinedTextField(
                value = adminState.searchQuery,
                onValueChange = {
                    onSearchQueryChange(it)
                    onLoadForms(1, it.takeIf { it.isNotBlank() }, null, null)
                },
                label = { Text("Search by name or email") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Statistics
        Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                        CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
        ) {
            Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatisticItem("Total Forms", adminState.totalCount.toString())
                StatisticItem("Current Page", "${adminState.currentPage}/${adminState.totalPages}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Horizontal Scrollable Table
        Column {
            // Table Header
            Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors =
                            CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
            ) {
                Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()).padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HeaderCell(text = "Name", width = 150.dp)
                    HeaderCell(text = "Email", width = 200.dp)
                    HeaderCell(text = "Birthday", width = 120.dp)
                    HeaderCell(text = "Submitted", width = 120.dp)
                    HeaderCell(text = "Actions", width = 80.dp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Content
            if (adminState.isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (adminState.consentForms.isEmpty()) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Box(
                            modifier = Modifier.fillMaxWidth().padding(32.dp),
                            contentAlignment = Alignment.Center
                    ) { Text("No consent forms found") }
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    items(adminState.consentForms) { form ->
                        ConsentFormRow(
                                form = form,
                                onDelete = { showDeleteDialog = form },
                                onClick = { onFormClick(form) }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pagination
        if (adminState.totalPages > 1) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                        onClick = {
                            onLoadForms(
                                    adminState.currentPage - 1,
                                    adminState.searchQuery.takeIf { it.isNotBlank() },
                                    null,
                                    null
                            )
                        },
                        enabled = adminState.currentPage > 1
                ) { Text("Previous") }

                Text("Page ${adminState.currentPage} of ${adminState.totalPages}")

                OutlinedButton(
                        onClick = {
                            onLoadForms(
                                    adminState.currentPage + 1,
                                    adminState.searchQuery.takeIf { it.isNotBlank() },
                                    null,
                                    null
                            )
                        },
                        enabled = adminState.currentPage < adminState.totalPages
                ) { Text("Next") }
            }
        }
    }

    // Delete confirmation dialog
    showDeleteDialog?.let { form ->
        AlertDialog(
                onDismissRequest = { showDeleteDialog = null },
                title = { Text("Delete Consent Form") },
                text = {
                    Text(
                            "Are you sure you want to delete the consent form for ${form.firstName} ${form.lastName}?"
                    )
                },
                confirmButton = {
                    TextButton(
                            onClick = {
                                form.id?.let { onDeleteForm(it) }
                                showDeleteDialog = null
                            }
                    ) { Text("Delete") }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = null }) { Text("Cancel") }
                }
        )
    }

    // Error handling
    adminState.error?.let { error ->
        LaunchedEffect(error) {
            // You could show a snackbar here
        }
    }
}

@Composable
private fun StatisticItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
        )
        Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun HeaderCell(
        text: String,
        width: androidx.compose.ui.unit.Dp,
        modifier: Modifier = Modifier
) {
    Box(
            modifier = modifier.width(width).padding(horizontal = 4.dp),
            contentAlignment = Alignment.CenterStart
    ) { Text(text = text, fontWeight = FontWeight.Bold) }
}

@Composable
private fun ConsentFormRow(
        form: ConsentFormData,
        onDelete: () -> Unit,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
) {
    Card(
            modifier = modifier.fillMaxWidth().clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()).padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                    modifier = Modifier.width(150.dp).padding(horizontal = 4.dp),
                    contentAlignment = Alignment.CenterStart
            ) {
                Text(
                        text = "${form.firstName} ${form.lastName}",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                    modifier = Modifier.width(200.dp).padding(horizontal = 4.dp),
                    contentAlignment = Alignment.CenterStart
            ) { Text(text = form.email, maxLines = 2, overflow = TextOverflow.Ellipsis) }
            Box(
                    modifier = Modifier.width(120.dp).padding(horizontal = 4.dp),
                    contentAlignment = Alignment.CenterStart
            ) {
                Text(
                        text = formatDateToDDMMYYYY(form.birthday),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                    modifier = Modifier.width(120.dp).padding(horizontal = 4.dp),
                    contentAlignment = Alignment.CenterStart
            ) {
                Text(
                        text = formatDateTimeToDDMMYYYY(form.submittedAt),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                )
            }
            Box(modifier = Modifier.width(80.dp), contentAlignment = Alignment.Center) {
                IconButton(onClick = onDelete) {
                    Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
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
                val inputFormat = SimpleDateFormat(formatPattern, Locale.getDefault())
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
