package com.example.vkpasswordmanager.data.use_case

import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import com.example.vkpasswordmanager.domain.use_case.DeleteSiteUseCase

class DeleteSiteUseCaseImpl(
    private val siteRepository: SiteRepository
): DeleteSiteUseCase {
    override suspend fun invoke(site: Site) {
        return siteRepository.deleteSite(site)
    }
}