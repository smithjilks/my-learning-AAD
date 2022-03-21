package com.smith.telemed

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL =
    "http://e79d-41-89-4-199.ngrok.io/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MedicineApiService {

    @Multipart
    @GET("drugs")
    suspend fun getMedicineInfo(@Query("code") serialCode: String): ResponseModel
}


object MedicineApi {
    val retrofitService: MedicineApiService = retrofit.create(MedicineApiService::class.java)
}