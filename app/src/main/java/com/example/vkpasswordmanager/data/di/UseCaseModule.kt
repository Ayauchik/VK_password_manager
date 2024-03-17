package com.example.vkpasswordmanager.data.di

import com.example.vkpasswordmanager.data.use_case.DeleteSiteUseCaseImpl
import com.example.vkpasswordmanager.data.use_case.GetSitesOrderedByNameUseCaseImpl
import com.example.vkpasswordmanager.data.use_case.GetSitesOrderedByUsageTimeUseCaseImpl
import com.example.vkpasswordmanager.data.use_case.InsertSiteUseCaseImpl
import com.example.vkpasswordmanager.data.use_case.UpsertSiteUseCaseImpl
import com.example.vkpasswordmanager.domain.use_case.DeleteSiteUseCase
import com.example.vkpasswordmanager.domain.use_case.GetSitesOrderedByNameUseCase
import com.example.vkpasswordmanager.domain.use_case.GetSitesOrderedByUsageTimeUseCase
import com.example.vkpasswordmanager.domain.use_case.InsertSiteUseCase
import com.example.vkpasswordmanager.domain.use_case.UpsertSiteUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetSitesOrderedByNameUseCase> { GetSitesOrderedByNameUseCaseImpl(get()) }
    factory<DeleteSiteUseCase> {DeleteSiteUseCaseImpl(get())}
    factory<InsertSiteUseCase> {InsertSiteUseCaseImpl(get())}
    factory<UpsertSiteUseCase> {UpsertSiteUseCaseImpl(get())}
    factory<GetSitesOrderedByUsageTimeUseCase> { GetSitesOrderedByUsageTimeUseCaseImpl(get()) }
}