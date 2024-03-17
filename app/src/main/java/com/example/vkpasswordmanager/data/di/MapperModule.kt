package com.example.vkpasswordmanager.data.di


import com.example.vkpasswordmanager.data.mapper.SiteMapperImpl
import com.example.vkpasswordmanager.domain.mapper.SiteMapper
import org.koin.dsl.module

val mapperModule = module {
    factory<SiteMapper> { SiteMapperImpl() }
}