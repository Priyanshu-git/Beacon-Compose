package com.dev.beacon.data.repository

import com.dev.beacon.data.remote.dto.LoginResponse
import com.dev.beacon.data.remote.dto.OtpResponse

interface BeaconRepository {
    suspend fun login(email: String, password: String): LoginResponse
    suspend fun verifyOtp(email: String, otp: String): OtpResponse
}
