package com.example.consentform.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.consentform.ui.screens.*
import com.example.consentform.viewmodel.ConsentFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsentFormNavigation(
        navController: NavHostController = rememberNavController(),
        viewModel: ConsentFormViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val adminState by viewModel.adminState.collectAsState()
    val formData by viewModel.formData.collectAsState()
    val selectedForm by viewModel.selectedForm.collectAsState()

    var isAdminMode by remember { mutableStateOf(false) }

    Column {
        // Top App Bar with mode toggle
        TopAppBar(
                title = { Text(if (isAdminMode) "Admin Panel" else "Consent Form") },
                actions = {
                    Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(end = 8.dp)
                    ) {
                        FilterChip(
                                onClick = {
                                    isAdminMode = false
                                    navController.navigate("personal_info") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                },
                                label = { Text("Client") },
                                selected = !isAdminMode,
                                leadingIcon = {
                                    Icon(Icons.Default.Person, contentDescription = "Client Mode")
                                }
                        )
                        FilterChip(
                                onClick = {
                                    isAdminMode = true
                                    navController.navigate("admin") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                },
                                label = { Text("Admin") },
                                selected = isAdminMode,
                                leadingIcon = {
                                    Icon(Icons.Default.Settings, contentDescription = "Admin Mode")
                                }
                        )
                    }
                }
        )

        // Navigation Content
        NavHost(
                navController = navController,
                startDestination = "personal_info",
                modifier = Modifier.weight(1f)
        ) {
            // Client Flow
            composable("personal_info") {
                PersonalInfoScreen(
                        firstName = formData.firstName,
                        lastName = formData.lastName,
                        email = formData.email,
                        birthday = formData.birthday,
                        onFirstNameChange = viewModel::updateFirstName,
                        onLastNameChange = viewModel::updateLastName,
                        onEmailChange = viewModel::updateEmail,
                        onBirthdayChange = viewModel::updateBirthday,
                        onNext = { navController.navigate("consent") },
                        isNextEnabled = uiState.isStep1Valid
                )
            }

            composable("consent") {
                ConsentScreen(
                        hasAgreed = formData.hasAgreed,
                        onAgreementChange = viewModel::updateAgreement,
                        onSignatureChange = viewModel::updateSignature,
                        onPrevious = { navController.popBackStack() },
                        onNext = { navController.navigate("summary") },
                        isNextEnabled = uiState.isStep2Valid
                )
            }

            composable("summary") {
                SummaryScreen(
                        formData = formData,
                        onPrevious = { navController.popBackStack() },
                        onSubmit = { viewModel.submitConsentForm() },
                        isLoading = uiState.isLoading
                )

                // Navigate to thank you screen on successful submission
                LaunchedEffect(uiState.isSubmitted) {
                    if (uiState.isSubmitted) {
                        navController.navigate("thank_you") {
                            popUpTo("personal_info") { inclusive = true }
                        }
                    }
                }
            }

            composable("thank_you") {
                ThankYouScreen(
                        onStartOver = {
                            viewModel.resetForm()
                            navController.navigate("personal_info") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                )
            }

            // Admin Flow
            composable("admin") {
                AdminScreen(
                        adminState = adminState,
                        onLoadForms = viewModel::loadConsentForms,
                        onDeleteForm = viewModel::deleteConsentForm,
                        onSearchQueryChange = viewModel::updateSearchQuery,
                        onFormClick = { form ->
                            viewModel.selectForm(form)
                            navController.navigate("consent_detail")
                        }
                )
            }

            composable("consent_detail") {
                selectedForm?.let { form ->
                    ConsentFormDetailScreen(
                            consentForm = form,
                            onBack = { navController.popBackStack() },
                            onUpdateNames = { firstName, lastName ->
                                form.id?.let { id ->
                                    viewModel.updateConsentFormNames(id, firstName, lastName)
                                }
                            }
                    )
                }
            }
        }

        // Error handling with Snackbar
        val snackbarHostState = remember { SnackbarHostState() }

        SnackbarHost(hostState = snackbarHostState)

        uiState.error?.let { error ->
            LaunchedEffect(error) {
                snackbarHostState.showSnackbar(message = error, duration = SnackbarDuration.Long)
                viewModel.clearError()
            }
        }

        adminState.error?.let { error ->
            LaunchedEffect(error) {
                snackbarHostState.showSnackbar(message = error, duration = SnackbarDuration.Long)
                viewModel.clearError()
            }
        }
    }
}
