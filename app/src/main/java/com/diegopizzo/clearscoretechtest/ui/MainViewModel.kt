package com.diegopizzo.clearscoretechtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegopizzo.network.base.Result
import com.diegopizzo.network.interactor.ICreditScoreInteractor
import com.diegopizzo.network.model.CreditReportInfo
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val interactor: ICreditScoreInteractor,
    private val disposables: CompositeDisposable,
    private val subscriberScheduler: Scheduler = Schedulers.io(),
    private val observerScheduler: Scheduler = AndroidSchedulers.mainThread()
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