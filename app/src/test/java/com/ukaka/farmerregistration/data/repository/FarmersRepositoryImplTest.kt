package com.ukaka.farmerregistration.data.repository

import com.google.common.truth.Truth.assertThat
import com.ukaka.farmerregistration.data.FarmerDao
import com.ukaka.farmerregistration.data.FarmerEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FarmersRepositoryImplTest {

    private val mockDao: FarmerDao = mock()
    private val farmersRepository = FarmersRepositoryImpl(mockDao)

    @Test
    fun `insertFarmer should call dao insertFarmer`() = runBlocking{
        val farmer = FarmerEntity(1, "John Doe", "1234567890")

        farmersRepository.insertFarmer(farmer)

        verify(mockDao).insertFarmer(farmer)
    }

    @Test
    fun `getAllFarmers should return flow of farmers from dao`() = runBlocking {
        val farmers = listOf(FarmerEntity(1, "John Doe", "1234567890"))
        val flowFarmers = flow { emit(farmers) }

        `when`(mockDao.getAllFarmers()).thenReturn(flowFarmers)

        val result = farmersRepository.getAllFarmers().first()

        assertThat(farmers).isEqualTo(result)
    }

}