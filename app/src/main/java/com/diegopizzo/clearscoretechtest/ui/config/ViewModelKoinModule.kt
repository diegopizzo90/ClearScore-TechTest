package com.diegopizzo.clearscoretechtest.ui.config

import com.diegopizzo.clearscoretechtest.ui.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), CompositeDisposable()) }
}