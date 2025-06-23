package com.dev.beacon.data.repository

import com.dev.beacon.data.remote.NetworkResult
import com.dev.beacon.data.remote.dto.LoginResponse
import com.dev.beacon.data.remote.dto.OtpResponse

interface BeaconRepository {
    suspend fun login(email: String, password: String): NetworkResult<LoginResponse>
    suspend fun verifyOtp(email: String, otp: String): NetworkResult<OtpResponse>
}
