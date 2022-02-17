package com.diegopizzo.clearscoretechtest.ui.config

import com.diegopizzo.clearscoretechtest.BuildConfig
import com.diegopizzo.network.service.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): BaseUrl {
        return BaseUrl(BuildConfig.BASE_URL)
    }
}