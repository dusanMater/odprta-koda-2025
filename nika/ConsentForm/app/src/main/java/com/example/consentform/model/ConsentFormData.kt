package com.example.consentform.model

import kotlinx.serialization.Serializable

@Serializable
data class ConsentFormData(
    val id: Long? = null,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val birthday: String = "", // Format: YYYY-MM-DD
    val hasAgreed: Boolean = false,
    val signature: String = "", // Base64 encoded signature image
    val submittedAt: String? = null
)

@Serializable
data class ConsentFormResponse(
    val success: Boolean,
    val message: String,
    val data: ConsentFormData? = null
)

@Serializable
data class ConsentFormsResponse(
    val success: Boolean,
    val message: String,
    val data: List<ConsentFormData> = emptyList(),
    val total: Int = 0,
    val currentPage: Int = 1,
    val totalPages: Int = 1
) 