package com.example.locationlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocationEntity::class], version = 1)
abstract class RoomAppDb: RoomDatabase() {

    abstract fun locationDao(): LocationDao?

    companion object{
        private var INSTANSE:RoomAppDb? = null

        fun getAppDatabase(context: Context):RoomAppDb?{
            if (INSTANSE == null){
                INSTANSE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANSE
        }
    }
}