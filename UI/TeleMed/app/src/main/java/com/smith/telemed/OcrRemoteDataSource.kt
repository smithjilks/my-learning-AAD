package com.smith.telemed

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class OcrRemoteDataSource(
    private val ocrAPI: OcrApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    /**
     * This executes on an IO-optimized thread pool, the function is main-safe.
     */
    suspend fun fetchSerialNumber(image: MultipartBody.Part): ResponseModel =
    // Move the execution to an IO-optimized thread since the ApiService
        // doesn't support coroutines and makes synchronous requests.
        withContext(ioDispatcher) {
            ocrAPI.retrofitService.getSerialNumber(image)
        }


}
