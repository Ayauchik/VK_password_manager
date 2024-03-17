package com.example.vkpasswordmanager.data.mapper

import com.example.vkpasswordmanager.data.db.room.entity.SiteEntity
import com.example.vkpasswordmanager.domain.mapper.SiteMapper
import com.example.vkpasswordmanager.domain.models.Site

class SiteMapperImpl : SiteMapper {
    override suspend fun toDomain(site: SiteEntity): Site {
        return Site(
            id = site.id,
            name = site.name,
            url = site.url,
            password = site.password,
            mostUsed = site.mostUsed
        )
    }

    override suspend fun toEntity(site: Site): SiteEntity {
        return SiteEntity(
            id = site.id,
            name = site.name,
            url = site.url,
            password = site.password,
            mostUsed = site.mostUsed
        )
    }

//    override suspend fun SiteStateFromUIToData(siteUIState: SiteUIState): SiteState {
//        return SiteState(
//            sites = siteUIState.sites.map { toEntity(it) },
//            name = siteUIState.name,
//            url = siteUIState.url,
//            iconUrl = siteUIState.iconUrl,
//            password = siteUIState.password,
//            isAddingSite = siteUIState.isAddingSite,
//            sortType = siteUIState.sortType
//        )
//    }
//
//    override suspend fun SiteStateFromDataToUI(siteState: SiteState): SiteUIState {
//        return SiteUIState(
//            sites = siteState.sites.map { toDomain(it) },
//            name = siteState.name,
//            url = siteState.url,
//            iconUrl = siteState.iconUrl,
//            password = siteState.password,
//            isAddingSite = siteState.isAddingSite,
//            sortType = siteState.sortType
//        )
//    }
//
//    override suspend fun SiteEventFromUIToData(siteUiEvent: SiteUiEvent): SiteEvent {
//        return when (siteUiEvent) {
//            is SiteUiEvent.SaveSite -> SiteEvent.SaveSite(toEntity(siteUiEvent.site))
//            is SiteUiEvent.SetName -> SiteEvent.SetName(siteUiEvent.name)
//            is SiteUiEvent.SetUrl -> SiteEvent.SetUrl(siteUiEvent.url)
//            is SiteUiEvent.SetIconUrl -> SiteEvent.SetIconUrl(siteUiEvent.iconUrl)
//            is SiteUiEvent.SetPassword -> SiteEvent.SetPassword(siteUiEvent.password)
//            SiteUiEvent.ShowDialog -> SiteEvent.ShowDialog
//            SiteUiEvent.HideDialog -> SiteEvent.HideDialog
//            is SiteUiEvent.SortSites -> SiteEvent.SortSites(siteUiEvent.sortType)
//            is SiteUiEvent.DeleteSite -> SiteEvent.DeleteSite(toEntity(siteUiEvent.site))
//            else -> {SiteEvent.HideDialog}
//        }
//    }
//
//    override suspend fun SiteEventFromDataToUI(siteEvent: SiteEvent): SiteUiEvent {
//        return when (siteEvent) {
//            is SiteEvent.DeleteSite -> SiteUiEvent.SaveSite(toDomain(siteEvent.siteEntity))
//            SiteEvent.HideDialog -> SiteUiEvent.HideDialog
//            is SiteEvent.SaveSite -> SiteUiEvent.SaveSite(toDomain(siteEvent.siteEntity))
//            is SiteEvent.SetIconUrl -> SiteUiEvent.SetIconUrl(siteEvent.iconUrl)
//            is SiteEvent.SetName -> SiteUiEvent.SetName(siteEvent.name)
//            is SiteEvent.SetPassword -> SiteUiEvent.SetPassword(siteEvent.password)
//            is SiteEvent.SetUrl -> SiteUiEvent.SetUrl(siteEvent.url)
//            SiteEvent.ShowDialog -> SiteUiEvent.ShowDialog
//            is SiteEvent.SortSites -> SiteUiEvent.SortSites(siteEvent.sortType)
//        }
//    }
}