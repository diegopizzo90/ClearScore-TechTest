package com.diegopizzo.network.creator

import com.diegopizzo.network.model.CreditReportInfo
import com.diegopizzo.network.model.CreditScoreModel

internal interface ICreditScoreCreator {
    /**
     * Function used to convert and prepare data model to the UI layer
     */
    fun createCreditReportInfoModel(model: CreditScoreModel): CreditReportInfo
}
