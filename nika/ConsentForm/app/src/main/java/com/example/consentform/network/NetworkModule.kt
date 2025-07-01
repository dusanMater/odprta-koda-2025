package com.example.consentform.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

        // Replace with your actual API base URL
        // Using 10.0.2.2 for Android emulator (standard emulator -> host mapping)
        private const val BASE_URL = "http://192.168.50.30/consentform/api/"

        private val loggingInterceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        private val retrofit =
                Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)
}
