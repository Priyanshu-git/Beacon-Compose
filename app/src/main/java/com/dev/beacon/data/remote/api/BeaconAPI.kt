package com.dev.beacon.data.remote.api

import com.dev.beacon.data.remote.dto.LoginRequest
import com.dev.beacon.data.remote.dto.LoginResponse
import com.dev.beacon.data.remote.dto.OtpRequest
import com.dev.beacon.data.remote.dto.OtpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface BeaconApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/verify-otp")
    suspend fun verifyOtp(@Body request: OtpRequest): OtpResponse

    // Add more endpoints as needed
}
