package com.sophia.search.Room

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "infor")
data class Infor(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "phnumber")
    var phnumber: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
