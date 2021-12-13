package com.diegopizzo.clearscoretechtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.diegopizzo.network.base.Result
import com.diegopizzo.network.interactor.ICreditScoreInteractor
import com.diegopizzo.network.model.CreditReportInfo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var interactor: ICreditScoreInteractor

    @Mock
    private lateinit var stateObserver: Observer<MainViewState>

    @Before
    fun setUp() {
        viewModel = MainViewModel(
            interactor, CompositeDisposable(), Schedulers.trampoline(), Schedulers.trampoline()
        ).apply {
            viewStates.observeForever(stateObserver)
        }
    }

    @Test
    fun getCreditScoreInformation_successResult_verifyCreditScoreInfo() {
        `when`(interactor.getCreditScoreInformation()).thenReturn(Single.just(successResult))

        viewModel.getCreditScoreInfo()

        //Initial result
        verify(stateObserver).onChanged(MainViewState(null))
        //Final result
        verify(stateObserver).onChanged(MainViewState(creditScoreInfo))
    }

    @Test
    fun getCreditScoreInformation_errorResult_verifyCreditScoreInfo() {
        `when`(interactor.getCreditScoreInformation()).thenReturn(Single.error(Exception("any error")))

        viewModel.getCreditScoreInfo()
        verify(stateObserver, times(2)).onChanged(MainViewState(null))
    }

    companion object {
        private val creditScoreInfo = CreditReportInfo(321, 700)
        private val successResult = Result.Success(creditScoreInfo)
    }
}