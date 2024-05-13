package com.ukaka.farmerregistration.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "farmer")
data class FarmerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "crop_type")
    val cropType: String
)