package com.diegopizzo.network.service

import com.diegopizzo.network.model.CreditScoreModel
import com.diegopizzo.network.service.NetworkConstant.CREDIT_SCORE_ENDPOINT
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

internal interface ApiService {
    /**
     * API request to retrieve user's credit score information
     */
    @GET(CREDIT_SCORE_ENDPOINT)
    fun getCreditScoreInformation(): Single<Response<CreditScoreModel>>
}