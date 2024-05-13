package com.ukaka.farmerregistration.data.repository

import com.ukaka.farmerregistration.data.FarmerDao
import com.ukaka.farmerregistration.data.FarmerEntity
import com.ukaka.farmerregistration.domain.repository.FarmersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FarmersRepositoryImpl @Inject constructor(
    private val dao: FarmerDao
): FarmersRepository {
    override suspend fun insertFarmer(farmer: FarmerEntity) {
        dao.insertFarmer(farmer)
    }

    override fun getAllFarmers(): Flow<List<FarmerEntity>> {
        return dao.getAllFarmers()
    }
}