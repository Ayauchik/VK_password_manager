package com.example.vkpasswordmanager.domain.use_case

import com.example.vkpasswordmanager.domain.models.Site
import kotlinx.coroutines.flow.Flow

interface GetSitesOrderedByUsageTimeUseCase {
    suspend operator fun invoke(): Flow<List<Site>>
}