package com.diegopizzo.network.interator

import com.diegopizzo.network.base.Result
import com.diegopizzo.network.creator.CreditScoreCreator
import com.diegopizzo.network.interactor.CreditScoreInteractor
import com.diegopizzo.network.interactor.ICreditScoreInteractor
import com.diegopizzo.network.model.CreditReportInfo
import com.diegopizzo.network.model.CreditScoreModel
import com.diegopizzo.network.service.ApiService
import io.reactivex.Single
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CreditScoreInteractorTest {

    private lateinit var interactor: ICreditScoreInteractor

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        interactor = CreditScoreInteractor(apiService, CreditScoreCreator())
    }

    @Test
    fun getCreditScoreInformation_successResult_assertEqualsTrue() {
        `when`(apiService.getCreditScoreInformation()).thenReturn(Single.just(successResponse))
        val actualValue = interactor.getCreditScoreInformation().blockingGet()
        assertEquals(expectedSuccessResult, actualValue)
    }

    @Test
    fun getCreditScoreInformation_networkErrorResult_assertEqualsTrue() {
        `when`(apiService.getCreditScoreInformation()).thenReturn(Single.error(Exception("any error")))
        val actualValue = interactor.getCreditScoreInformation().blockingGet()
        assertEquals(Result.Error<CreditReportInfo>(), actualValue)
    }

    @Test
    fun getCreditScoreInformation_serverErrorResult_assertEqualsTrue() {
        `when`(apiService.getCreditScoreInformation()).thenReturn(
            Single.just(Response.error(500, ResponseBody.create(null, "")))
        )
        val actualValue = interactor.getCreditScoreInformation().blockingGet()
        assertEquals(Result.Error<CreditReportInfo>(500), actualValue)
    }

    companion object {
        private val successResponse = Response.success(
            CreditScoreModel(
                CreditReportInfo(514, 700)
            )
        )

        private val expectedSuccessResult = Result.Success(
            CreditReportInfo(514, 700)
        )
    }
}