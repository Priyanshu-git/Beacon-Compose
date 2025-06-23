package com.dev.beacon.data.remote.dto

data class OtpResponse(
    val success: Boolean,
    val token: String,
    val userId: String,
    val name: String
)
