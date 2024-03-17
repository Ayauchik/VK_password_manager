package com.example.vkpasswordmanager.data.repository

import com.example.vkpasswordmanager.data.db.room.dao.SiteDao
import com.example.vkpasswordmanager.domain.mapper.SiteMapper
import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SiteRepositoryImpl(
    private val dao: SiteDao,
    private val siteMapper: SiteMapper
): SiteRepository {
    override suspend fun getSitedOrderedByName(): Flow<List<Site>> {
        return dao.getSitesOrderedByFirstName().map { siteEntities ->
            siteEntities.map { siteEntity ->
                siteMapper.toDomain(siteEntity)
            }
        }
    }

    override suspend fun getSitedOrderedByUsageTimes(): Flow<List<Site>> {
        return dao.getSitesOrderedByUsageTimes().map { siteEntities->
            siteEntities.map { siteEntity ->
                siteMapper.toDomain(siteEntity)
            }
        }
    }

    override suspend fun getSiteById(siteId: Long): Flow<Site> {
        return dao.getSiteById(siteId).map {
            siteMapper.toDomain(it)
        }
    }

    override suspend fun insertSite(site: Site) {
        dao.insertSite(siteMapper.toEntity(site))
    }

    override suspend fun deleteSite(site: Site) {
        dao.deleteSite(siteMapper.toEntity(site))
    }

    override suspend fun upsertSite(site: Site) {
        dao.upsertSite(siteMapper.toEntity(site))
    }

//    override suspend fun SiteStateFromUIToData(siteUiState: SiteUIState): SiteState {
//        return siteMapper.SiteStateFromUIToData(siteUiState)
//    }
//
//    override suspend fun SiteStateFromDataToUI(siteState: SiteState): SiteUIState {
//        return siteMapper.SiteStateFromDataToUI(siteState)
//    }
//
//    override suspend fun SiteEventFromUiToData(siteUiEvent: SiteUiEvent): SiteEvent {
//        return siteMapper.SiteEventFromUIToData(siteUiEvent)
//    }
//
//    override suspend fun SiteEventFromDataToUi(siteEvent: SiteEvent): SiteUiEvent {
//        return siteMapper.SiteEventFromDataToUI(siteEvent)
//    }
}