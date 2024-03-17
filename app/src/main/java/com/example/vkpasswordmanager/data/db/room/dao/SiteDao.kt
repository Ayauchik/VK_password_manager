package com.example.vkpasswordmanager.data.db.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.vkpasswordmanager.data.db.room.entity.SiteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSite(siteEntity: SiteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertSite(siteEntity: SiteEntity)

    @Delete
    suspend fun deleteSite(siteEntity: SiteEntity)

    @Query("SELECT * FROM siteentity ORDER BY name ASC")
    fun getSitesOrderedByFirstName(): Flow<List<SiteEntity>>

    @Query("SELECT * FROM siteentity ORDER BY mostUsed ASC")
    fun getSitesOrderedByUsageTimes(): Flow<List<SiteEntity>>

    @Query("SELECT * FROM siteentity WHERE id = :id")
    fun getSiteById(id: Long): Flow<SiteEntity>
}