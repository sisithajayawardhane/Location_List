package com.example.locationlist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locationInfo")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int = 0,
    @ColumnInfo(name = "location_name") val location_name:String,
    @ColumnInfo(name = "latitude") val latitude:String,
    @ColumnInfo(name = "longitude") val longitude:String

    )
