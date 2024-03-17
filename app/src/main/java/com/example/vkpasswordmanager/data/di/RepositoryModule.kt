package com.example.vkpasswordmanager.data.di

import com.example.vkpasswordmanager.data.repository.SiteRepositoryImpl
import com.example.vkpasswordmanager.domain.repository.SiteRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<SiteRepository> { SiteRepositoryImpl(get(), get()) }
}