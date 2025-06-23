package com.dev.beacon.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.beacon.ui.viewmodel.AppViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(viewModel: AppViewModel = hiltViewModel()) {
    val location = viewModel.location.collectAsState().value

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    val markerState = rememberMarkerState(position = location)

    // Update marker position when location changes
    LaunchedEffect(location) {
        markerState.position = location
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLng(location)
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = markerState,
            title = "Current Location",
            snippet = "Lat: ${location.latitude}, Lng: ${location.longitude}"
        )
    }
}

