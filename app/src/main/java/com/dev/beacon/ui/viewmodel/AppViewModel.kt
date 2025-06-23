package com.dev.beacon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.beacon.data.repository.BeaconRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: BeaconRepository
) : ViewModel(){
    private val _location = MutableStateFlow(LatLng(28.651057, 77.343355))
    val location: StateFlow<LatLng> = _location.asStateFlow()

    fun startLocationUpdates() {
        viewModelScope.launch {
            while (true){
                delay(2000)
                _location.value = LatLng(location.value.latitude+0.001, location.value.longitude+0.001)
            }
        }
    }

}