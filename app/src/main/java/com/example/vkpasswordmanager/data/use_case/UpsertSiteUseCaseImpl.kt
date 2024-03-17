package com.example.vkpasswordmanager.data.use_case

import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import com.example.vkpasswordmanager.domain.use_case.UpsertSiteUseCase

class UpsertSiteUseCaseImpl(
    private val siteRepository: SiteRepository
): UpsertSiteUseCase {
    override suspend fun invoke(site: Site) {
        return siteRepository.upsertSite(site)
    }
}