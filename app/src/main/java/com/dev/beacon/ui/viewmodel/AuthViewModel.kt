package com.dev.beacon.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.dev.beacon.data.repository.BeaconRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: BeaconRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun setEmail(value: String) {
        _email.value = value
    }
}