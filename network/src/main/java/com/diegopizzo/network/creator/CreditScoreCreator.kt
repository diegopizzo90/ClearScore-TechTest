package com.diegopizzo.network.creator

import com.diegopizzo.network.model.CreditReportInfo
import com.diegopizzo.network.model.CreditScoreModel

internal class CreditScoreCreator : ICreditScoreCreator {
    override fun createCreditReportInfoModel(model: CreditScoreModel): CreditReportInfo {
        model.creditReportInfo.apply {
            return CreditReportInfo(score, maxScoreValue)
        }
    }
}