package com.diegopizzo.network.creator

import com.diegopizzo.network.model.CreditReportInfo
import com.diegopizzo.network.model.CreditScoreModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CreditScoreCreatorTest {

    private lateinit var creator: ICreditScoreCreator

    @Before
    fun setUp() {
        creator = CreditScoreCreator()
    }

    @Test
    fun createCreditReportInfoModel_creditScoreModel_assertEqualsTrue() {
        val actualValue = creator.createCreditReportInfoModel(model)
        assertEquals(expectedCreditReportInfoModel, actualValue)
    }

    companion object {
        private val model = CreditScoreModel(
            CreditReportInfo(123, 323)
        )

        private val expectedCreditReportInfoModel = CreditReportInfo(123, 323)
    }
}