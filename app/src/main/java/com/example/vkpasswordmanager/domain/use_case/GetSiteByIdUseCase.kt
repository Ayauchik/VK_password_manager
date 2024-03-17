package com.example.vkpasswordmanager.domain.use_case

import com.example.vkpasswordmanager.domain.models.Site
import kotlinx.coroutines.flow.Flow

interface GetSiteByIdUseCase {
    suspend operator fun invoke(siteId: Long): Flow<Site>
}