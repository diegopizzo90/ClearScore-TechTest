package com.diegopizzo.clearscoretechtest.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.diegopizzo.clearscoretechtest.R
import com.diegopizzo.network.base.Result
import com.diegopizzo.network.interactor.ICreditScoreInteractor
import com.diegopizzo.network.model.CreditReportInfo
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Mock
    private lateinit var interactor: ICreditScoreInteractor

    private val resources = InstrumentationRegistry.getInstrumentation().targetContext.resources

    @Before
    fun setUp() {
        val interactorModule = module {
            factory { interactor }
        }
        loadKoinModules(interactorModule)
    }

    @Test
    fun activityLaunched_success_checkUiState() {
        `when`(interactor.getCreditScoreInformation()).thenReturn(
            Single.just(Result.Success(CreditReportInfo(100, 600)))
        )
        scenario = launchActivity()
        onView(withId(R.id.pb_external_donut)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_internal_donut)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_top_text)).check(matches(withText(R.string.your_credit_score_is)))
        onView(withId(R.id.tv_center_main_text)).check(matches(withText("100")))

        val maxCreditScoreLabel = resources.getString(R.string.out_of, "600")
        onView(withId(R.id.tv_bottom_text)).check(matches(withText(maxCreditScoreLabel)))
    }

    @Test
    fun activityLaunched_error_checkUiState() {
        `when`(interactor.getCreditScoreInformation()).thenReturn(Single.error(Exception("any error")))
        scenario = launchActivity()
        onView(withId(R.id.pb_external_donut)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_internal_donut)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_top_text)).check(matches(withText(R.string.your_credit_score_is)))
        onView(withId(R.id.tv_center_main_text)).check(matches(withText(R.string.not_available)))

        val maxCreditScoreLabel =
            resources.getString(R.string.out_of, resources.getString(R.string.not_available))
        onView(withId(R.id.tv_bottom_text)).check(matches(withText(maxCreditScoreLabel)))
    }
}