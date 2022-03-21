package com.smith.telemed

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class MedicineRemoteDataSource(
    private val medAPI: MedicineApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    /**
     * This executes on an IO-optimized thread pool, the function is main-safe.
     */
    suspend fun fetchMedicineInfo(code: String): ResponseModel =
    // Move the execution to an IO-optimized thread since the ApiService
        // doesn't support coroutines and makes synchronous requests.
        withContext(ioDispatcher) {
            medAPI.retrofitService.getMedicineInfo(code)
        }


}
