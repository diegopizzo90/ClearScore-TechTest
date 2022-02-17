package com.diegopizzo.clearscoretechtest.ui.config

import com.diegopizzo.clearscoretechtest.BuildConfig
import com.diegopizzo.network.service.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object MainViewModelModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @SubscriberScheduler
    @Provides
    fun provideSubscriberScheduler(): Scheduler {
        return Schedulers.io()
    }

    @ObserverScheduler
    @Provides
    fun provideObserverScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SubscriberScheduler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ObserverScheduler

