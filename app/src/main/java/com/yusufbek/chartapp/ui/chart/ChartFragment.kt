package com.yusufbek.chartapp.ui.chart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.yusufbek.chartapp.R
import com.yusufbek.chartapp.databinding.FragmentChartBinding
import com.yusufbek.chartapp.localdb.ChartDataEntity
import com.yusufbek.chartapp.ui.ChartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment : Fragment(), OnChartValueSelectedListener {

    private lateinit var binding: FragmentChartBinding

    private val viewModel: ChartViewModel by viewModels()

    private var mChartType = "line"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChartBinding.inflate(inflater, container, false)

        viewModel.getChartDataByType("line").observe(viewLifecycleOwner) {
            setUpLineChart(it)
        }

        binding.toggleButton.check(R.id.buttonLineChart)

        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            when (checkedId) {
                R.id.buttonLineChart -> {
                    if (isChecked) {
                        mChartType = "line"
                        binding.lineChart.visibility = View.VISIBLE
                        binding.barChart.visibility = View.GONE
                        binding.pieChart.visibility = View.GONE
                        viewModel.getChartDataByType("line").observe(viewLifecycleOwner) {
                            setUpLineChart(it)
                        }
                    }
                }
                R.id.buttonBarChart -> {
                    if (isChecked) {
                        mChartType = "bar"
                        binding.lineChart.visibility = View.GONE
                        binding.barChart.visibility = View.VISIBLE
                        binding.pieChart.visibility = View.GONE
                        viewModel.getChartDataByType("bar").observe(viewLifecycleOwner) {
                            setUpBarChart(it)
                        }
                    }
                }
                R.id.buttonPieChart -> {
                    if (isChecked) {
                        mChartType = "pie"
                        binding.lineChart.visibility = View.GONE
                        binding.barChart.visibility = View.GONE
                        binding.pieChart.visibility = View.VISIBLE
                        viewModel.getChartDataByType("pie").observe(viewLifecycleOwner) {
                            setUpPieChart(it)
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun setUpLineChart(data: List<ChartDataEntity>) {
        binding.lineChart.setBackgroundColor(Color.WHITE)
        binding.lineChart.description.isEnabled = false
        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setOnChartValueSelectedListener(this)
        binding.lineChart.setDrawGridBackground(false)
        binding.lineChart.isDragEnabled = true
        binding.lineChart.isScaleXEnabled = true
        binding.lineChart.isScaleYEnabled = true
        binding.lineChart.setPinchZoom(true)
        binding.lineChart.axisRight.isEnabled = false

        val values = ArrayList<Entry>()
        for (item in data) {
            values.add(Entry(item.xValue!!, item.yValue!!))
        }

        val set1: LineDataSet?

        if (binding.lineChart.data != null && binding.lineChart.data.dataSetCount > 0) {
            set1 = binding.lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            binding.lineChart.data.notifyDataChanged()
            binding.lineChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "Dataset1")
            set1.setDrawIcons(false)
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)
            val lineData = LineData(dataSets)
            binding.lineChart.data = lineData
        }
        binding.lineChart.invalidate()
//        val legend = binding.lineChart.legend
//        legend.form = Legend.LegendForm.LINE
    }

    private fun setUpBarChart(data: List<ChartDataEntity>) {
        binding.barChart.setTouchEnabled(true)
        binding.barChart.setOnChartValueSelectedListener(this)
        binding.barChart.setDrawBarShadow(false)
        binding.barChart.setDrawValueAboveBar(true)
        binding.barChart.description.isEnabled = false
        binding.barChart.setMaxVisibleValueCount(20)
        binding.barChart.setPinchZoom(false)
        binding.barChart.setDrawGridBackground(false)

        binding.barChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            granularity = 1f
            labelCount = 7
            setDrawGridLines(false)
        }
        binding.barChart.axisLeft.apply {
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            labelCount = 8
            spaceTop = 15f
            axisMinimum = 0f
            setDrawGridLines(false)
        }
        binding.barChart.axisRight.apply {
            axisLineColor = Color.WHITE
            labelCount = 8
            spaceTop = 15f
            axisMinimum = 0f
            textColor = Color.WHITE
            setDrawGridLines(false)
        }
        binding.barChart.apply {
            description.text = "Y over X"
            legend.isEnabled = false
        }

        val values = ArrayList<BarEntry>()
        for (item in data) {
            values.add(BarEntry(item.xValue!!, item.yValue!!))
        }
        val barDataset: BarDataSet
        if (binding.barChart.data != null && binding.barChart.data.dataSetCount > 0) {
            barDataset = binding.barChart.data.getDataSetByIndex(0) as BarDataSet
            barDataset.values = values
            binding.barChart.data.notifyDataChanged()
            binding.barChart.notifyDataSetChanged()
        } else {
            barDataset = BarDataSet(values, "Y over x")
            barDataset.setDrawIcons(false)
            val barData = BarData(barDataset)
            barData.setValueTextSize(10f)
            barData.barWidth = 0.9f
            binding.barChart.data = barData
        }
        binding.barChart.invalidate()
//        val legend = binding.barChart.legend
//        legend.form = Legend.LegendForm.LINE
    }

    private fun setUpPieChart(data: List<ChartDataEntity>) {
        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.description.isEnabled = false
        binding.pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        binding.pieChart.dragDecelerationFrictionCoef = 0.95f
        binding.pieChart.isDrawHoleEnabled = true
        binding.pieChart.setHoleColor(Color.WHITE)
        binding.pieChart.setTransparentCircleColor(Color.WHITE)
        binding.pieChart.setTransparentCircleAlpha(110)
        binding.pieChart.holeRadius = 58f
        binding.pieChart.transparentCircleRadius = 61f
        binding.pieChart.setDrawCenterText(false)
        binding.pieChart.rotationAngle = 0f
        binding.pieChart.isRotationEnabled = true
        binding.pieChart.isHighlightPerTapEnabled = true
        binding.pieChart.setOnChartValueSelectedListener(this)
        binding.pieChart.animateY(1400, Easing.EaseInOutQuad)

        val array = ArrayList<PieEntry>()
        for (item in data) {
            array.add(PieEntry(item.pieValue!!))
        }
        val pieDataset = PieDataSet(array, "Value distribution")
        pieDataset.setDrawIcons(false)
        pieDataset.sliceSpace = 3f
        pieDataset.iconsOffset = MPPointF(0f, 40f)
        pieDataset.selectionShift = 5f

        val colors: ArrayList<Int> = ArrayList()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        pieDataset.colors = colors

        val pieData = PieData(pieDataset)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(11f)
        pieData.setValueTextColor(Color.WHITE)
        binding.pieChart.data = pieData
        binding.pieChart.highlightValues(null)
        binding.pieChart.invalidate()
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Log.d("TAGGED", "we clicked on pieChart")
        val bundle = Bundle().apply {
            putSerializable("chartType", mChartType)
        }
        findNavController().navigate(R.id.action_chartFragment_to_listFragment, bundle)
    }

    override fun onNothingSelected() {

    }
}