package com.example.vkpasswordmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vkpasswordmanager.data.db.room.dao.SiteDao
import com.example.vkpasswordmanager.data.db.room.entity.SiteEntity

@Database(
    entities = [SiteEntity::class],
    version = 4
)
abstract class SiteDatabase : RoomDatabase(){
    abstract val dao: SiteDao
}