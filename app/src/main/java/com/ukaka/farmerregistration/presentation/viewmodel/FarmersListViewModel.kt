package com.ukaka.farmerregistration.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukaka.farmerregistration.data.FarmerEntity
import com.ukaka.farmerregistration.domain.repository.FarmersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FarmersListViewModel @Inject constructor(
    private val repository: FarmersRepository
): ViewModel() {

    private var _registeredFarmersModel: MutableStateFlow<List<FarmerEntity>> = MutableStateFlow(emptyList())
    val registeredFarmersModel: StateFlow<List<FarmerEntity>> = _registeredFarmersModel

    init {
        fetchRegisteredFarmers()
    }

    private fun fetchRegisteredFarmers() {
        viewModelScope.launch {
            val registeredFarmers = repository.getAllFarmers().first()
            _registeredFarmersModel.value = registeredFarmers
        }
    }
}