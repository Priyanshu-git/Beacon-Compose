package com.dev.beacon.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dev.beacon.presentation.navigation.authGraph
import com.dev.beacon.presentation.navigation.mainGraph

@Composable
fun BeaconApp() {
    val navController = rememberNavController()
    val isLoggedIn = remember { mutableStateOf(isUserLoggedIn()) } // Replace with real login state later

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn.value) "main" else "auth"
    ) {
        authGraph(navController)
        mainGraph(navController)
    }

}

fun isUserLoggedIn(): Boolean {
    return true
}

