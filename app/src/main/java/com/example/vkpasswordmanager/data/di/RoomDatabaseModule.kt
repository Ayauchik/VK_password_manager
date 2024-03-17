package com.example.vkpasswordmanager.data.di

import androidx.room.Room
import com.example.vkpasswordmanager.data.SiteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), SiteDatabase::class.java, name = ROOM_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    factory {
        val appRoomDatabase: SiteDatabase = get()
        appRoomDatabase.dao
    }

}

private const val ROOM_DB_NAME = "site.db"
