package com.example.vkpasswordmanager.ui.site

import com.example.vkpasswordmanager.domain.models.Site

sealed interface SiteUiEvent {
    object SaveSite: SiteUiEvent
    data class SetId(val id: Long): SiteUiEvent
    data class SetMostUsed(val mostUsed: Int): SiteUiEvent
    data class SetName(val name: String): SiteUiEvent
    data class SetUrl(val url: String): SiteUiEvent
    data class SetPassword(val password: String): SiteUiEvent
    object SetTruePasswordVisibility: SiteUiEvent

    object ShowDialog: SiteUiEvent
    data class ShowDialogEdit(val site: Site): SiteUiEvent
    object HideDialog: SiteUiEvent

    data class SortSites(val sortType: SortType): SiteUiEvent
    data class DeleteSite(val site: Site): SiteUiEvent
    object UpsertSite: SiteUiEvent

//    companion object {
//        fun fromUiEvent(uiEvent: SiteUiEvent): SiteEvent {
//            return when (uiEvent) {
//                is SiteUiEvent.SaveSite -> SaveSite(uiEvent.siteEntity)
//                is SiteUiEvent.SetName -> SetName(uiEvent.name)
//                is SiteUiEvent.SetUrl -> SetUrl(uiEvent.url)
//                is SiteUiEvent.SetIconUrl -> SetIconUrl(uiEvent.iconUrl)
//                is SiteUiEvent.SetPassword -> SetPassword(uiEvent.password)
//                is SiteUiEvent.ShowDialog -> ShowDialog
//                is SiteUiEvent.HideDialog -> HideDialog
//                is SiteUiEvent.SortSites -> SortSites(uiEvent.sortType)
//                is SiteUiEvent.DeleteSite -> DeleteSite(uiEvent.siteEntity)
//            }
//        }
//    }
}
