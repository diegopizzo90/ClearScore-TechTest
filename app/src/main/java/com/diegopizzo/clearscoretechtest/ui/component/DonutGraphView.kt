package com.diegopizzo.clearscoretechtest.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.diegopizzo.clearscoretechtest.R
import com.diegopizzo.clearscoretechtest.databinding.ComponentDonutGraphBinding

class DonutGraphView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding =
        ComponentDonutGraphBinding.inflate(LayoutInflater.from(context), this, true)

    private val labelTextSize: Float
    private val creditScoreTextSize: Float

    init {
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DonutGraphView)
        labelTextSize =
            styledAttributes.getFloat(R.styleable.DonutGraphView_label_text_size, defaultLabelSize)
        creditScoreTextSize = styledAttributes.getFloat(
            R.styleable.DonutGraphView_credit_score_text_size, defaultCreditScoreSize
        )
        binding.tvCreditScoreLabel.textSize = labelTextSize
        binding.tvMaxCreditScore.textSize = labelTextSize
        binding.tvCreditScore.textSize = creditScoreTextSize
        styledAttributes.recycle()
    }

    fun updateChartValue(
        progressValue: Int,
        progressMaxValue: Int,
        creditScore: String,
        maxCreditScore: String
    ) {
        binding.pbInternalDonut.progress =
            calculatePercentageProgress(progressValue, progressMaxValue)
        binding.tvCreditScore.text = creditScore
        binding.tvMaxCreditScore.text =
            context.getString(R.string.out_of, maxCreditScore)
    }

    private fun calculatePercentageProgress(progressValue: Int, progressMaxValue: Int): Int {
        return ((progressValue.toDouble() / progressMaxValue) * 100).toInt()
    }

    companion object {
        private const val defaultLabelSize = 20F
        private const val defaultCreditScoreSize = 64F
    }
}