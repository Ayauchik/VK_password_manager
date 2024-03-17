package com.example.vkpasswordmanager.data.db.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class SiteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    //@ColumnInfo(name = "name", index = true, )
    val name: String,
    val url: String,
    val password: String,
    val mostUsed: Int
)
