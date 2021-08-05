package com.sophia.search.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Infor::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun inforDao(): InforDao

}