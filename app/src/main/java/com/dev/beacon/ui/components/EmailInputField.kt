package com.dev.beacon.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EmailInputField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Email",
    errorMessage: String? = null
) {
    val isError = errorMessage!= null && errorMessage.isNotEmpty()

    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text(label) },
        singleLine = true,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        modifier = modifier.fillMaxWidth()
    )

    if (isError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}
