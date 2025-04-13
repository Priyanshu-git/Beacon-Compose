package com.dev.beacon.utils

import android.util.Patterns

object LoginUtils {
    fun getInvalidEmailMessage(email: String): String? {
        return if (email.isEmpty()) {
            "Email cannot be empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Invalid email address"
        } else {
            return null
        }
    }
}