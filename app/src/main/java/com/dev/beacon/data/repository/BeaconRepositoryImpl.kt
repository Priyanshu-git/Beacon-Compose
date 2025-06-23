package com.dev.beacon.data.repository

import com.dev.beacon.data.remote.api.BeaconApi
import com.dev.beacon.data.remote.dto.LoginRequest
import com.dev.beacon.data.remote.dto.LoginResponse
import com.dev.beacon.data.remote.dto.OtpRequest
import com.dev.beacon.data.remote.dto.OtpResponse
import jakarta.inject.Inject

class BeaconRepositoryImpl @Inject constructor(
    private val api: BeaconApi
) : BeaconRepository {

    override suspend fun login(email: String, password: String): LoginResponse {
        return api.login(LoginRequest(email, password))
    }

    override suspend fun verifyOtp(email: String, otp: String): OtpResponse {
        return api.verifyOtp(OtpRequest(email, otp))
    }
}
