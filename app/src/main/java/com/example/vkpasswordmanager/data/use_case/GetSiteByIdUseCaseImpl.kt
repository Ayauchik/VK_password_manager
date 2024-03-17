package com.example.vkpasswordmanager.data.use_case

import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import com.example.vkpasswordmanager.domain.use_case.GetSiteByIdUseCase
import kotlinx.coroutines.flow.Flow

class GetSiteByIdUseCaseImpl(
    private val siteRepository: SiteRepository
): GetSiteByIdUseCase {
    override suspend fun invoke(siteId: Long): Flow<Site> {
        return siteRepository.getSiteById(siteId)
    }
}