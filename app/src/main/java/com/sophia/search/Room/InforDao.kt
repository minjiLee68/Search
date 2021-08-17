package com.sophia.search.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InforDao {

    @Query("SELECT * FROM infor ORDER BY id DESC")
    fun getAll(): LiveData<List<Infor>>

    @Insert
    fun insert(infor: Infor)

    @Delete
    fun delete(infor: Infor)

    @Update
    fun update(infor: Infor)

    @Query("SELECT * FROM infor WHERE name LIKE :name or LOWER(name) like LOWER(:name)")
    fun getListAllByName(name: String): LiveData<List<Infor>>
}