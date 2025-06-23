package com.dev.beacon.presentation.screen.home

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.beacon.R
import com.dev.beacon.ui.viewmodel.AppViewModel
import com.dev.beacon.utils.Logger
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

private const val TAG = "MapScreen"

@Composable
fun MapScreen(viewModel: AppViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val location = viewModel.location.collectAsState().value
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }
    val markerState = rememberMarkerState(position = location)

    // Update marker position when location changes
    LaunchedEffect(location) {
        Logger.d(TAG, "LaunchedEffect: location")
        markerState.position = location
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(location, 15f),
            durationMs = 300
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.fetchCurrentLocation()
        } else {
            Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        Logger.d(TAG, "LaunchedEffect: Unit")
        if (ContextCompat.checkSelfPermission(
               context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.fetchCurrentLocation()
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = markerState,
            title = "Current Location",
            snippet = "Lat: ${location.latitude}, Lng: ${location.longitude}",
            icon = BitmapDescriptorFactory.fromResource(R.drawable.green_dot_16)
        )
    }
}

