package com.diegopizzo.network.model

data class CreditReportInfo(val score: Int, val maxScoreValue: Int)
internal data class CreditScoreModel(val creditReportInfo: CreditReportInfo)