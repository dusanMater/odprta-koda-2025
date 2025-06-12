package com.example.consentform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consentform.model.ConsentFormData
import com.example.consentform.network.NetworkModule
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConsentFormViewModel : ViewModel() {

    private val apiService = NetworkModule.apiService

    // UI State
    private val _uiState = MutableStateFlow(ConsentFormUiState())
    val uiState: StateFlow<ConsentFormUiState> = _uiState.asStateFlow()

    // Admin State
    private val _adminState = MutableStateFlow(AdminUiState())
    val adminState: StateFlow<AdminUiState> = _adminState.asStateFlow()

    // Current form data
    private val _formData = MutableStateFlow(ConsentFormData())
    val formData: StateFlow<ConsentFormData> = _formData.asStateFlow()

    // Selected form for detail view
    private val _selectedForm = MutableStateFlow<ConsentFormData?>(null)
    val selectedForm: StateFlow<ConsentFormData?> = _selectedForm.asStateFlow()

    fun updateFirstName(firstName: String) {
        _formData.value = _formData.value.copy(firstName = firstName)
        validateStep1()
    }

    fun updateLastName(lastName: String) {
        _formData.value = _formData.value.copy(lastName = lastName)
        validateStep1()
    }

    fun updateEmail(email: String) {
        _formData.value = _formData.value.copy(email = email)
        validateStep1()
    }

    fun updateBirthday(birthday: String) {
        _formData.value = _formData.value.copy(birthday = birthday)
        validateStep1()
    }

    fun updateAgreement(hasAgreed: Boolean) {
        _formData.value = _formData.value.copy(hasAgreed = hasAgreed)
        validateStep2()
    }

    fun updateSignature(signature: String) {
        _formData.value = _formData.value.copy(signature = signature)
        validateStep2()
    }

    private fun validateStep1() {
        val data = _formData.value
        val isValid =
                data.firstName.isNotBlank() &&
                        data.lastName.isNotBlank() &&
                        data.email.isNotBlank() &&
                        isValidEmail(data.email) &&
                        data.birthday.isNotBlank() &&
                        isValidDate(data.birthday)

        _uiState.value = _uiState.value.copy(isStep1Valid = isValid)
    }

    private fun validateStep2() {
        val data = _formData.value
        val isValid = data.hasAgreed && data.signature.isNotBlank()
        _uiState.value = _uiState.value.copy(isStep2Valid = isValid)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidDate(date: String): Boolean {
        return try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun submitConsentForm() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                println("ConsentFormViewModel: Starting form submission...")
                println("ConsentFormViewModel: Form data = ${_formData.value}")

                val response = apiService.submitConsentForm(_formData.value)
                println("ConsentFormViewModel: Response code = ${response.code()}")
                println("ConsentFormViewModel: Response body = ${response.body()}")

                if (response.isSuccessful && response.body()?.success == true) {
                    println("ConsentFormViewModel: Submission successful!")
                    _uiState.value = _uiState.value.copy(isLoading = false, isSubmitted = true)
                } else {
                    val errorMsg =
                            response.body()?.message ?: "Submission failed (${response.code()})"
                    println("ConsentFormViewModel: Submission failed: $errorMsg")
                    _uiState.value = _uiState.value.copy(isLoading = false, error = errorMsg)
                }
            } catch (e: Exception) {
                println("ConsentFormViewModel: Exception occurred: ${e.message}")
                e.printStackTrace()
                _uiState.value =
                        _uiState.value.copy(isLoading = false, error = e.message ?: "Network error")
            }
        }
    }

    fun resetForm() {
        _formData.value = ConsentFormData()
        _uiState.value = ConsentFormUiState()
    }

    // Admin functions
    fun loadConsentForms(
            page: Int = 1,
            search: String? = null,
            sortBy: String? = null,
            sortOrder: String? = null
    ) {
        _adminState.value = _adminState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val response = apiService.getConsentForms(page, 30, search, sortBy, sortOrder)
                if (response.isSuccessful && response.body()?.success == true) {
                    val body = response.body()!!
                    _adminState.value =
                            _adminState.value.copy(
                                    isLoading = false,
                                    consentForms = body.data,
                                    currentPage = body.currentPage,
                                    totalPages = body.totalPages,
                                    totalCount = body.total
                            )
                } else {
                    _adminState.value =
                            _adminState.value.copy(
                                    isLoading = false,
                                    error = response.body()?.message ?: "Failed to load data"
                            )
                }
            } catch (e: Exception) {
                _adminState.value =
                        _adminState.value.copy(
                                isLoading = false,
                                error = e.message ?: "Network error"
                        )
            }
        }
    }

    fun deleteConsentForm(id: Long) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteConsentForm(id)
                if (response.isSuccessful && response.body()?.success == true) {
                    // Reload the current page
                    loadConsentForms(_adminState.value.currentPage)
                } else {
                    _adminState.value =
                            _adminState.value.copy(
                                    error = response.body()?.message ?: "Failed to delete"
                            )
                }
            } catch (e: Exception) {
                _adminState.value = _adminState.value.copy(error = e.message ?: "Network error")
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _adminState.value = _adminState.value.copy(searchQuery = query)
    }

    fun selectForm(form: ConsentFormData) {
        _selectedForm.value = form
    }

    fun updateConsentFormNames(id: Long, firstName: String, lastName: String) {
        viewModelScope.launch {
            try {
                // Get the current form to maintain other data
                val currentForm = _selectedForm.value ?: return@launch

                // Create updated form data
                val updatedForm = currentForm.copy(firstName = firstName, lastName = lastName)

                val response = apiService.updateConsentForm(id, updatedForm)
                if (response.isSuccessful && response.body()?.success == true) {
                    // Update the selected form with new data
                    _selectedForm.value = updatedForm

                    // Reload the forms list to reflect changes
                    loadConsentForms(_adminState.value.currentPage)
                } else {
                    _adminState.value =
                            _adminState.value.copy(
                                    error = response.body()?.message ?: "Failed to update"
                            )
                }
            } catch (e: Exception) {
                _adminState.value = _adminState.value.copy(error = e.message ?: "Network error")
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
        _adminState.value = _adminState.value.copy(error = null)
    }
}

data class ConsentFormUiState(
        val isLoading: Boolean = false,
        val isStep1Valid: Boolean = false,
        val isStep2Valid: Boolean = false,
        val isSubmitted: Boolean = false,
        val error: String? = null
)

data class AdminUiState(
        val isLoading: Boolean = false,
        val consentForms: List<ConsentFormData> = emptyList(),
        val currentPage: Int = 1,
        val totalPages: Int = 1,
        val totalCount: Int = 0,
        val searchQuery: String = "",
        val sortBy: String? = null,
        val sortOrder: String? = null,
        val error: String? = null
)
