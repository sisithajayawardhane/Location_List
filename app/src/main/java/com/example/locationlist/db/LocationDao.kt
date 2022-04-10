package com.example.locationlist.db

import androidx.room.*

@Dao
interface LocationDao {

    @Query("SELECT * FROM locationInfo ORDER BY id DESC")
    fun getAllLocationInfo(): List<LocationEntity>?

    @Insert
    fun insertLocation(location:LocationEntity?)

    @Delete
    fun deleteLocation(location: LocationEntity?)

    @Update
    fun updateLocation(location: LocationEntity?)
}