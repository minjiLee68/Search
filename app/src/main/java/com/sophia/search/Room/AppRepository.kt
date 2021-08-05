package com.sophia.search.Room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room

class AppRepository(application: Application) {

    companion object {
        private const val DATABASE_NAME = "AppDatabase"
    }

    val db = Room.databaseBuilder(
        application, AppDatabase::class.java, DATABASE_NAME
    ).build()

    val inforDao = db.inforDao()

    fun getAllinfor(): LiveData<List<Infor>> =
        inforDao.getAll()

    fun insertinfor(infor: Infor) {
        inforDao.insert(infor)
    }

    fun deleteinfor(infor: Infor) {
        inforDao.delete(infor)
    }

    fun updateinfor(infor: Infor) {
        inforDao.update(infor)
    }

}