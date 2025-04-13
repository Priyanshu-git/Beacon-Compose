package com.dev.beacon.presentation.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.beacon.common.AppConstants
import com.dev.beacon.ui.components.LoadingIconButton

@Composable
@Preview
fun OTPVerificationScreen(navController: NavController = rememberNavController()) {
    var otp by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Verify OTP", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))

            Text("Enter the ${AppConstants.OTP_LENGTH}-digit code sent to your email.", style = MaterialTheme.typography.bodyLarge)

            Row {
                Text("Didn't receive code?", style = MaterialTheme.typography.bodyLarge)
                Text(" Resend", style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            var errorState by remember { mutableStateOf(false) }
            OtpTextField(
                otp = otp,
                onOtpChange = { if (it.length <= AppConstants.OTP_LENGTH) {
                    otp = it
                    errorState = false
                } },
                errorState = errorState
            )

            Spacer(modifier = Modifier.height(32.dp))

            var isLoading by remember { mutableStateOf(false) }
            LoadingIconButton(
                isLoading = isLoading,
                onClick = {
                    if (otp.length == AppConstants.OTP_LENGTH) {
                        isLoading = true
                        navController.navigate("home") {
                            popUpTo("otp") { inclusive = true }
                        }
                    } else {
                        errorState = true
                    }
                },
                icon = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Continue"
            )

        }
    }
}

@Composable
fun OtpTextField(
    otp: String,
    onOtpChange: (String) -> Unit,
    errorState: Boolean = false,
) {
    BasicTextField(
        value = otp,
        onValueChange = onOtpChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(AppConstants.OTP_LENGTH) { index ->
                        val char = otp.getOrNull(index)?.toString() ?: ""
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (errorState) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(
                                    MaterialTheme.colorScheme.surface,
                                    RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = char, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    )
}
