package com.diegopizzo.clearscoretechtest.config

import android.app.Application
import com.diegopizzo.clearscoretechtest.BuildConfig
import com.diegopizzo.clearscoretechtest.ui.config.viewModelModule
import com.diegopizzo.network.creator.creatorModule
import com.diegopizzo.network.interactor.interactorModule
import com.diegopizzo.network.service.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ClearScoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ClearScoreApplication)
            modules(
                retrofitModule(BuildConfig.BASE_URL),
                creatorModule,
                interactorModule,
                viewModelModule
            )
        }
    }
}