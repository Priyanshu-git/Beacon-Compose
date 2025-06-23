package com.dev.beacon.ui.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.beacon.data.repository.BeaconRepository
import com.dev.beacon.utils.Logger
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: BeaconRepository,
    @ApplicationContext private val appContext: Context,
) : ViewModel(){
    private val TAG = "AppViewModel"
    private val _location = MutableStateFlow(LatLng(28.651057, 77.343355))
    val location: StateFlow<LatLng> = _location.asStateFlow()

    fun fetchCurrentLocation(){
        Logger.d(TAG, "fetchCurrentLocation")
        viewModelScope.launch {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)
            if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Logger.d(TAG, "Permission granted. Starting fetch.")
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { loc ->
                        loc?.let {
                            _location.value = LatLng(it.latitude, it.longitude)
                        }
                    }
            } else{
                Logger.d(TAG, "Permission not granted.")
            }
        }
    }

}