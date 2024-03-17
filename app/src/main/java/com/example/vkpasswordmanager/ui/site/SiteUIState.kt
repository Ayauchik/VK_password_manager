package com.example.vkpasswordmanager.ui.site

import com.example.vkpasswordmanager.domain.models.Site

data class SiteUIState(
    val sites: List<Site> = emptyList(),
    val id: Long = 0,
    val name: String = "",
    val url: String = "",
    var password: String = "",
    val mostUsed: Int = 0,
    val isAddingSite: Boolean = false,
    val isEditingSite: Boolean = false,
    val passwordVisibility: Boolean = false,
    val sortType: SortType = SortType.NAME
)
