package com.diegopizzo.clearscoretechtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegopizzo.clearscoretechtest.ui.config.MainViewModelModule
import com.diegopizzo.clearscoretechtest.ui.config.ObserverScheduler
import com.diegopizzo.clearscoretechtest.ui.config.SubscriberScheduler
import com.diegopizzo.network.base.Result
import com.diegopizzo.network.interactor.ICreditScoreInteractor
import com.diegopizzo.network.model.CreditReportInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: ICreditScoreInteractor,
    private val disposables: CompositeDisposable,
    @SubscriberScheduler private val subscriberScheduler: Scheduler,
    @ObserverScheduler private val observerScheduler: Scheduler
) : ViewModel() {

    private val _viewStates: MutableLiveData<MainViewState> = MutableLiveData()
    val viewStates: LiveData<MainViewState> = _viewStates

    private var _viewState: MainViewState? = null
    var viewState: MainViewState
        get() = _viewState
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            _viewState = value
            _viewStates.value = value
        }

    init {
        viewState = MainViewState()
    }

    fun getCreditScoreInfo() {
        disposables.add(
            interactor.getCreditScoreInformation()
                .subscribeOn(subscriberScheduler)
                .observeOn(observerScheduler)
                .subscribe({ result ->
                    when (result) {
                        is Result.Success -> onSuccess(result.data)
                        is Result.Error -> onError()
                    }
                }, {
                    onError()
                })
        )
    }

    private fun onSuccess(info: CreditReportInfo) {
        viewState = viewState.copy(creditReportInfo = info)
    }

    private fun onError() {
        viewState = viewState.copy(creditReportInfo = null)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

data class MainViewState(val creditReportInfo: CreditReportInfo? = null)