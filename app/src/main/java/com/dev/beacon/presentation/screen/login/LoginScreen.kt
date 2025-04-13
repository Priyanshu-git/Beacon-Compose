package com.dev.beacon.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.beacon.R
import com.dev.beacon.ui.components.EmailInputField
import com.dev.beacon.ui.components.LoadingIconButton
import com.dev.beacon.utils.LoginUtils

@Composable
@Preview
fun LoginScreen(navController: NavHostController = rememberNavController()) {
    var isLoading by remember { mutableStateOf(false) }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login/Signup",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(148.dp)
                    .background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lighthouse),
                    contentDescription = "Lighthouse",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            var email by remember { mutableStateOf("") }
            var errorState: String? by remember { mutableStateOf(null) }
            Column(modifier = Modifier.padding(24.dp)) {
                EmailInputField(
                    email = email,
                    onEmailChange = {
                        email = it
                        errorState = null
                    },
                    errorMessage = errorState
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            LoadingIconButton(
                isLoading = isLoading,
                onClick = {
                    errorState = LoginUtils.getInvalidEmailMessage(email)
                    if (errorState == null) {
                        isLoading = true
                        navController.navigate("otp") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                icon = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Continue"
            )
        }
    }

}
