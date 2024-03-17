package com.example.vkpasswordmanager.data.use_case

import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import com.example.vkpasswordmanager.domain.use_case.InsertSiteUseCase

class InsertSiteUseCaseImpl(
    private val siteRepository: SiteRepository
): InsertSiteUseCase {
    override suspend fun invoke(site: Site) {
        return siteRepository.insertSite(site)
    }
}