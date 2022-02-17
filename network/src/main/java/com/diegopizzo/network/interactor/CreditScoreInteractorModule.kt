package com.diegopizzo.network.interactor

import com.diegopizzo.network.creator.ICreditScoreCreator
import com.diegopizzo.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object CreditScoreInteractorModule {

    @Provides
    fun provideInteractor(
        apiService: ApiService,
        creator: ICreditScoreCreator
    ): ICreditScoreInteractor {
        return CreditScoreInteractor(apiService, creator)
    }
}