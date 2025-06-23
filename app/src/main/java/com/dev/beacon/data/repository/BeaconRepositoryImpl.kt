package com.dev.beacon.data.repository

import com.dev.beacon.data.remote.NetworkResult
import com.dev.beacon.data.remote.api.BeaconApi
import com.dev.beacon.data.remote.dto.LoginRequest
import com.dev.beacon.data.remote.dto.LoginResponse
import com.dev.beacon.data.remote.dto.OtpRequest
import com.dev.beacon.data.remote.dto.OtpResponse
import jakarta.inject.Inject
import retrofit2.Response

class BeaconRepositoryImpl @Inject constructor(
    private val api: BeaconApi
) : BeaconRepository {

    suspend fun <T> handleRequest(
        apiCall: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Success(body)
                } else {
                    NetworkResult.Error(response.code(), "Empty body")
                }
            } else {
                NetworkResult.Error(response.code(), response.message())
            }
        } catch (e: retrofit2.HttpException) {
            NetworkResult.Error(e.code(), e.message())
        } catch (e: Exception) {
            NetworkResult.Exception(e)
        }
    }


    override suspend fun login(email: String, password: String): NetworkResult<LoginResponse> {
        return handleRequest {
            api.login(LoginRequest(email, password))
        }
    }

    override suspend fun verifyOtp(email: String, otp: String): NetworkResult<OtpResponse> {
        return handleRequest {
            api.verifyOtp(OtpRequest(email, otp))
        }
    }
}
