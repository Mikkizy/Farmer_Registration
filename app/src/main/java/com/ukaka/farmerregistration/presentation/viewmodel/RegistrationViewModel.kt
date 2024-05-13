package com.ukaka.farmerregistration.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukaka.farmerregistration.data.FarmerEntity
import com.ukaka.farmerregistration.domain.repository.FarmersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: FarmersRepository
): ViewModel() {

    fun saveFarmerInfo(name: String, cropType: String) {
        viewModelScope.launch {
            repository.insertFarmer(FarmerEntity(fullName = name, cropType = cropType))
        }
    }

}