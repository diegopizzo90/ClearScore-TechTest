package com.diegopizzo.network.interactor

import com.diegopizzo.network.base.Result
import com.diegopizzo.network.model.CreditReportInfo
import io.reactivex.Single

interface ICreditScoreInteractor {
    /**
     * Function that retrieves credit score information from ApiService.
     * This is also useful to create a separation from data layer and decoupling the business logic.
     */
    fun getCreditScoreInformation(): Single<Result<CreditReportInfo>>
}
