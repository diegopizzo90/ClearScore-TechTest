package com.diegopizzo.clearscoretechtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.diegopizzo.clearscoretechtest.R
import com.diegopizzo.clearscoretechtest.databinding.ActivityMainBinding
import com.diegopizzo.clearscoretechtest.ui.base.ActivityViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ActivityViewBinding<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewStates.observe(this, observeViewState())
        viewModel.getCreditScoreInfo()
    }

    private fun observeViewState() = Observer<MainViewState> {
        updateGraph(it.creditReportInfo?.score, it.creditReportInfo?.maxScoreValue)
    }

    private fun updateGraph(score: Int?, maxScoreValue: Int?) {
        val noCreditScoreAvailable = getString(R.string.not_available)
        binding.donutGraph.updateChartValue(
            progress = score ?: 0,
            progressMax = maxScoreValue ?: 0,
            topTextValue = getString(R.string.your_credit_score_is),
            mainCenterTextValue = score?.toString() ?: noCreditScoreAvailable,
            bottomTextValue = getString(
                R.string.out_of,
                maxScoreValue?.toString() ?: noCreditScoreAvailable
            )
        )
    }
}