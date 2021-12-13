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

    private val topTextSizeAttr: Float
    private val mainCenterTextSizeAttr: Float
    private val bottomTextSizeAttr: Float

    init {
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DonutGraphView)

        topTextSizeAttr = styledAttributes.getFloat(
            R.styleable.DonutGraphView_top_text_size,
            defaultTopBottomTextSize
        )
        mainCenterTextSizeAttr = styledAttributes.getFloat(
            R.styleable.DonutGraphView_center_main_text_size,
            defaultCenterMainTextSize
        )
        bottomTextSizeAttr = styledAttributes.getFloat(
            R.styleable.DonutGraphView_bottom_main_text_size,
            defaultTopBottomTextSize
        )

        binding.tvTopText.textSize = topTextSizeAttr
        binding.tvCenterMainText.textSize = mainCenterTextSizeAttr
        binding.tvBottomText.textSize = bottomTextSizeAttr
        styledAttributes.recycle()
    }

    /**
     * Function used to update the values of donut graph
     *
     * @param progress indicates the value of internal progress bar
     * @param progressMax indicates the maximum value reachable of the internal progress bar
     * @param topTextValue updates the text view located at the top center
     * @param mainCenterTextValue updates the text view in the center of the donut graph
     * @param bottomTextValue updates the text view located at the bottom center
     */
    fun updateChartValue(
        progress: Int,
        progressMax: Int,
        topTextValue: String,
        mainCenterTextValue: String,
        bottomTextValue: String
    ) {
        binding.pbInternalDonut.max = progressMax
        binding.pbInternalDonut.progress = progress
        binding.tvTopText.text = topTextValue
        binding.tvCenterMainText.text = mainCenterTextValue
        binding.tvBottomText.text = bottomTextValue
    }

    companion object {
        private const val defaultTopBottomTextSize = 20F
        private const val defaultCenterMainTextSize = 64F
    }
}