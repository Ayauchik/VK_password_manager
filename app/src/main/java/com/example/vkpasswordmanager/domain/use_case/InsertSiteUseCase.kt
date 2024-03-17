package com.example.vkpasswordmanager.domain.use_case

import com.example.vkpasswordmanager.domain.models.Site

interface InsertSiteUseCase {
    suspend operator fun invoke(site: Site)
}