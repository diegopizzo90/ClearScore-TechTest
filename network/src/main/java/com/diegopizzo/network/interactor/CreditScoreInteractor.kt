package com.diegopizzo.network.interactor

import com.diegopizzo.network.base.BaseInteractor
import com.diegopizzo.network.base.Result
import com.diegopizzo.network.creator.ICreditScoreCreator
import com.diegopizzo.network.model.CreditReportInfo
import com.diegopizzo.network.service.ApiService
import io.reactivex.Single
import javax.inject.Inject

internal class CreditScoreInteractor @Inject constructor(
    private val apiService: ApiService,
    private val creator: ICreditScoreCreator
) : BaseInteractor(), ICreditScoreInteractor {

    override fun getCreditScoreInformation(): Single<Result<CreditReportInfo>> {
        return apiService.getCreditScoreInformation().map {
            when (val result = handleResponse(it)) {
                is Result.Success -> Result.Success(creator.createCreditReportInfoModel(result.data))
                is Result.Error -> Result.Error(result.errorCode)
            }
        }.onErrorReturn {
            Result.Error()
        }
    }
}