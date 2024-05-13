package com.ukaka.farmerregistration.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FarmerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmer(farmerEntity: FarmerEntity)

    @Query("SELECT * FROM farmer")
    fun getAllFarmers(): Flow<List<FarmerEntity>>

}