package com.dev.beacon.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.dev.beacon.presentation.screen.home.HomeScreen
import com.dev.beacon.presentation.screen.login.LoginScreen
import com.dev.beacon.presentation.screen.login.OTPVerificationScreen

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(startDestination = "login", route = "auth") {
        composable("login") { LoginScreen(navController) }
        composable("otp") { OTPVerificationScreen(navController) }
    }
}

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation(startDestination = "home", route = "main") {
        composable("home") { HomeScreen(navController) }
    }
}
