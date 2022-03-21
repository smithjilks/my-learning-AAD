package com.smith.telemed

import com.squareup.moshi.Json

data class ResponseModel(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "valid") val valid: Boolean,
    @field:Json(name = "expired_text") val expired_text: Boolean,
    @field:Json(name = "response") val response: String,
)