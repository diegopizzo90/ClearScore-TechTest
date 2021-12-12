package com.diegopizzo.network.creator

import com.diegopizzo.network.model.CreditReportInfo
import com.diegopizzo.network.model.CreditScoreModel

internal class CreditScoreCreator : ICreditScoreCreator {
    override fun createCreditReportInfoModel(model: CreditScoreModel): CreditReportInfo {
        return model.creditReportInfo.apply {
            CreditReportInfo(score, maxScoreValue)
        }
    }
}