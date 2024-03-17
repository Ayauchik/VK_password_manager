package com.example.vkpasswordmanager.domain.repository

import com.example.vkpasswordmanager.domain.models.Site
import kotlinx.coroutines.flow.Flow

interface SiteRepository {
    suspend fun getSitedOrderedByName(): Flow<List<Site>>
    suspend fun getSitedOrderedByUsageTimes(): Flow<List<Site>>
    suspend fun getSiteById(siteId:Long): Flow<Site>
    suspend fun insertSite(site: Site)
    suspend fun deleteSite(site: Site)
    suspend fun upsertSite(site: Site)
}