package com.diegopizzo.network.service

import com.diegopizzo.network.model.CreditReportInfo
import com.diegopizzo.network.model.CreditScoreModel
import com.diegopizzo.network.service.NetworkConstant.CREDIT_SCORE_ENDPOINT
import com.diegopizzo.network.testutil.enqueueResponse
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ApiServiceTest {
    private lateinit var server: MockWebServer

    private lateinit var api: ApiService

    @Before
    fun setUp() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url(BASE_URL))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun after() {
        server.close()
    }

    private fun successResponse(fileName: String = "success_response.json"): Response<CreditScoreModel> {
        server.enqueueResponse(fileName, 200)
        return api.getCreditScoreInformation().blockingGet()
    }

    @Test
    fun getCreditScoreInformation_recordRequest_assertEqualsTrue() {
        successResponse()

        val request = server.takeRequest()
        assertEquals(request.path, BASE_URL + CREDIT_SCORE_ENDPOINT)
    }

    @Test
    fun getCreditScoreInformation_successResponse_assertEqualsTrue() {
        val response = successResponse()
        assertEquals(expectedSuccessResponse.body(), response.body())
    }

    @Test
    fun getCreditScoreInformation_serverErrorResponse_assertEqualsTrue() {
        server.enqueue(MockResponse().setResponseCode(500))
        val response = api.getCreditScoreInformation().blockingGet()
        assertEquals(null, response.body())
    }

    @Test
    fun getCreditScoreInformation_clientErrorResponse_assertEqualsTrue() {
        server.enqueue(MockResponse().setResponseCode(400))
        val response = api.getCreditScoreInformation().blockingGet()
        assertEquals(null, response.body())
    }

    companion object {
        private const val BASE_URL = "/"

        private val expectedSuccessResponse =
            Response.success(
                CreditScoreModel(
                    CreditReportInfo(514, 700)
                )
            )
    }
}