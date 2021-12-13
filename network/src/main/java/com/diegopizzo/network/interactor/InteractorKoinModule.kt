package com.diegopizzo.network.interactor

import org.koin.dsl.module

val interactorModule = module {
    factory<ICreditScoreInteractor> { CreditScoreInteractor(get(), get()) }
}