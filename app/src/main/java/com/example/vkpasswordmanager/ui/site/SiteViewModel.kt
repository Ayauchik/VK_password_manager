package com.example.vkpasswordmanager.ui.site

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.use_case.DeleteSiteUseCase
import com.example.vkpasswordmanager.domain.use_case.GetSitesOrderedByNameUseCase
import com.example.vkpasswordmanager.domain.use_case.GetSitesOrderedByUsageTimeUseCase
import com.example.vkpasswordmanager.domain.use_case.InsertSiteUseCase
import com.example.vkpasswordmanager.domain.use_case.UpsertSiteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class SiteViewModel(
    private val getSitesOrderedByNameUseCase: GetSitesOrderedByNameUseCase,
    private val getSitesOrderedByUsageTimeUseCase: GetSitesOrderedByUsageTimeUseCase,
    private val insertSiteUseCase: InsertSiteUseCase,
    private val deleteSiteUseCase: DeleteSiteUseCase,
    private val upsertSiteUseCase: UpsertSiteUseCase
) : ViewModel() {
    private val _sortType = MutableStateFlow(SortType.NAME)
    private val _state = MutableStateFlow(SiteUIState())


    private val _sites = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.NAME -> getSitesOrderedByNameUseCase()
                SortType.USAGE -> getSitesOrderedByUsageTimeUseCase()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _sortType, _sites) { state, sortType, sites ->
        state.copy(
            sites = sites,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SiteUIState())


    fun onEvent(UIevent: SiteUiEvent) {
        viewModelScope.launch {

            when (UIevent) {
                is SiteUiEvent.DeleteSite -> {
                    viewModelScope.launch {
                        deleteSiteUseCase(UIevent.site)
                    }
                }

                SiteUiEvent.HideDialog -> {
                    _state.update {
                        it.copy(
                            isAddingSite = false,
                            isEditingSite = false
                        )
                    }
                }

                is SiteUiEvent.SetName -> {
                    _state.update {
                        it.copy(
                            name = UIevent.name
                        )
                    }
                }

                is SiteUiEvent.SetPassword -> {
                    _state.update {
                        it.copy(
                            password = UIevent.password
                        )
                    }
                }

                is SiteUiEvent.SetUrl -> {
                    _state.update {
                        it.copy(
                            url = UIevent.url
                        )
                    }
                }

                SiteUiEvent.ShowDialog -> {
                    _state.update {
                        it.copy(
                            isAddingSite = true
                        )
                    }
                }

                is SiteUiEvent.SortSites -> {
                    _sortType.value = UIevent.sortType
                }

                is SiteUiEvent.SaveSite -> {
                    val name = state.value.name
                    val url = state.value.url
                    val password = state.value.password

                    if (name.isBlank() || url.isBlank()  || password.isBlank()) {
                        return@launch
                    }

                    val site = Site(
                        id = null,
                        name = name,
                        url = url,
                        password = password,
                        mostUsed = 0
                    )

                    viewModelScope.launch {
                        insertSiteUseCase(site)
                    }

                    _state.update {
                        it.copy(
                            isAddingSite = false,
                            name = "",
                            url = "",
                            password = "",
                        )
                    }
                }

                is SiteUiEvent.UpsertSite -> {
                    val name = state.value.name
                    val url = state.value.url
                    val password = state.value.password
                    val id = state.value.id
                    val mostUsed = state.value.mostUsed

                    if (name.isBlank() || url.isBlank()  || password.isBlank()) {
                        return@launch
                    }

                    val site = Site(
                        id = id,
                        name = name,
                        url = url,
                        password = password,
                        mostUsed = mostUsed
                    )

                    Log.e("update", "$site")

                    viewModelScope.launch {
                        upsertSiteUseCase(site)
                    }

                    _state.update {
                        it.copy(
                            isAddingSite = false,
                            isEditingSite = false,
                            name = "",
                            url = "",
                            password = ""
                        )
                    }
                }

                is SiteUiEvent.SetId -> {
                    _state.update {
                        it.copy(
                            id = UIevent.id
                        )
                    }
                }
                is SiteUiEvent.SetMostUsed -> {
                    _state.update {
                        it.copy(
                            mostUsed = UIevent.mostUsed
                        )
                    }
                }

                is SiteUiEvent.ShowDialogEdit -> {
                    val site = UIevent.site
                    _state.update {
                        it.copy(
                            isEditingSite = true,
                            id = site.id!!,
                            name = site.name,
                            url = site.url,
                            mostUsed = site.mostUsed,
                            password = site.password
                        )
                    }
                }

                SiteUiEvent.SetTruePasswordVisibility -> {
                    _state.update {
                        it.copy(
                            passwordVisibility = true
                        )
                    }
                }
            }
        }
    }
}