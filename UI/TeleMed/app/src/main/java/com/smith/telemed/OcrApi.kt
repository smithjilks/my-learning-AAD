package com.smith.telemed

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

private const val BASE_URL =
    "https://e657-41-89-4-199.ngrok.io/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val okHttpClient = OkHttpClient.Builder()
    .readTimeout(60, TimeUnit.SECONDS)
//    .protocols(listOf(Protocol.HTTP_1_1))
    .connectTimeout(60, TimeUnit.SECONDS)
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface OcrApiService {

    @Multipart
    @POST("upload")
    suspend fun getSerialNumber(@Part file: MultipartBody.Part): ResponseModel
}


object OcrApi {
    val retrofitService: OcrApiService = retrofit.create(OcrApiService::class.java)
}