package com.example.vkpasswordmanager.data.use_case

import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import com.example.vkpasswordmanager.domain.use_case.GetSitesOrderedByUsageTimeUseCase
import kotlinx.coroutines.flow.Flow

class GetSitesOrderedByUsageTimeUseCaseImpl(
    private val siteRepository: SiteRepository
) : GetSitesOrderedByUsageTimeUseCase {
    override suspend fun invoke(): Flow<List<Site>> {
        return siteRepository.getSitedOrderedByUsageTimes()
    }
}