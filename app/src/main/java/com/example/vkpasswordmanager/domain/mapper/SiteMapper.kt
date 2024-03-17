package com.example.vkpasswordmanager.domain.mapper

import com.example.vkpasswordmanager.data.db.room.entity.SiteEntity
import com.example.vkpasswordmanager.domain.models.Site

interface SiteMapper {
    suspend fun toDomain(site: SiteEntity): Site
    suspend fun toEntity(site: Site): SiteEntity
//    suspend fun SiteStateFromUIToData(siteUIState: SiteUIState): SiteState
//    suspend fun SiteStateFromDataToUI(siteState: SiteState): SiteUIState
//    suspend fun SiteEventFromUIToData(siteUiEvent: SiteUiEvent): SiteEvent
//    suspend fun SiteEventFromDataToUI(siteEvent: SiteEvent): SiteUiEvent
}