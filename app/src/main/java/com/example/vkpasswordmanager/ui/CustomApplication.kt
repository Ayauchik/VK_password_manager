package com.example.vkpasswordmanager.ui

import android.app.Application
import com.example.vkpasswordmanager.data.di.mapperModule
import com.example.vkpasswordmanager.data.di.repositoryModule
import com.example.vkpasswordmanager.data.di.roomDatabaseModule
import com.example.vkpasswordmanager.data.di.useCaseModule
import com.example.vkpasswordmanager.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    private val modulesToUse = listOf(
        viewModelModule,
        mapperModule,
        useCaseModule,
        roomDatabaseModule,
        repositoryModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CustomApplication)
            modules(modulesToUse)
        }
    }
}