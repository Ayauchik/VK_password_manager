package com.example.vkpasswordmanager.ui.di

import android.app.Application
//import com.example.vkpasswordmanager.ui.site.MasterKeyViewModel
import com.example.vkpasswordmanager.ui.site.SiteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{SiteViewModel(get(), get(), get(), get(), get())}
  //  viewModel { (application: Application) -> MasterKeyViewModel(application) }
}