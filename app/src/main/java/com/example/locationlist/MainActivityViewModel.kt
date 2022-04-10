package com.example.locationlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.locationlist.db.LocationEntity
import com.example.locationlist.db.RoomAppDb

class MainActivityViewModel(app:Application):AndroidViewModel(app) {

    lateinit var allLocations : MutableLiveData<List<LocationEntity>>
    init {
        allLocations = MutableLiveData()
        getAllLocations()
    }

    fun getAllLocationsObservers(): MutableLiveData<List<LocationEntity>> {
        return allLocations
    }

    fun getAllLocations(){
        val locationDao = RoomAppDb.getAppDatabase((getApplication()))?.locationDao()
        val list = locationDao?.getAllLocationInfo()

        allLocations.postValue(list)
    }

    fun insertLocationInfo(entity: LocationEntity){
        val locationDao = RoomAppDb.getAppDatabase((getApplication()))?.locationDao()
        locationDao?.insertLocation(entity)
        getAllLocations()
    }

    fun updateLocationInfo(entity: LocationEntity){
        val locationDao = RoomAppDb.getAppDatabase((getApplication()))?.locationDao()
        locationDao?.updateLocation(entity)
        getAllLocations()
    }

    fun deleteLocationInfo(entity: LocationEntity){
        val locationDao = RoomAppDb.getAppDatabase((getApplication()))?.locationDao()
        locationDao?.deleteLocation(entity)
        getAllLocations()
    }
}