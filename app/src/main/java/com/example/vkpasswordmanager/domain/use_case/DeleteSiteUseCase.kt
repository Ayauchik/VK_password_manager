package com.example.vkpasswordmanager.domain.use_case

import com.example.vkpasswordmanager.domain.models.Site

interface DeleteSiteUseCase {
    suspend operator fun invoke(site: Site)
}