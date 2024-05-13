package com.ukaka.farmerregistration.domain.repository

import com.ukaka.farmerregistration.data.FarmerEntity
import kotlinx.coroutines.flow.Flow

interface FarmersRepository {

    suspend fun insertFarmer(farmer: FarmerEntity)

    fun getAllFarmers(): Flow<List<FarmerEntity>>

}