package com.dev.beacon.data.remote.dto

data class LoginResponse(
    val userId: String,
    val name: String,
    val email: String,
    val token: String
)
