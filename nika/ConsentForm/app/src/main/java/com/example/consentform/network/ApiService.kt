package com.example.consentform.network

import com.example.consentform.model.ConsentFormData
import com.example.consentform.model.ConsentFormResponse
import com.example.consentform.model.ConsentFormsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

        @POST("submit.php")
        suspend fun submitConsentForm(
                @Body consentForm: ConsentFormData
        ): Response<ConsentFormResponse>

        @GET("forms.php")
        suspend fun getConsentForms(
                @Query("page") page: Int = 1,
                @Query("limit") limit: Int = 30,
                @Query("search") search: String? = null,
                @Query("sortBy") sortBy: String? = null,
                @Query("sortOrder") sortOrder: String? = null
        ): Response<ConsentFormsResponse>

        @PUT("update_consent.php/{id}")
        suspend fun updateConsentForm(
                @Path("id") id: Long,
                @Body consentForm: ConsentFormData
        ): Response<ConsentFormResponse>

        @DELETE("delete.php")
        suspend fun deleteConsentForm(@Query("id") id: Long): Response<ConsentFormResponse>
}
