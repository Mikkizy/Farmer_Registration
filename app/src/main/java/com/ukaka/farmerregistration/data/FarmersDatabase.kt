package com.ukaka.farmerregistration.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FarmerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FarmersDatabase : RoomDatabase() {
    abstract val farmerDao: FarmerDao
}