package com.example.vkpasswordmanager.data.use_case

import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import com.example.vkpasswordmanager.domain.use_case.GetSitesOrderedByNameUseCase
import kotlinx.coroutines.flow.Flow

class GetSitesOrderedByNameUseCaseImpl(
    private val siteRepository: SiteRepository
) : GetSitesOrderedByNameUseCase {
    override suspend fun invoke(): Flow<List<Site>> {
        return siteRepository.getSitedOrderedByName()
    }
}