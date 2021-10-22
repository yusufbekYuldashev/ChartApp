package com.yusufbek.chartapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yusufbek.chartapp.databinding.FragmentDetailBinding
import com.yusufbek.chartapp.localdb.ChartDataEntity
import com.yusufbek.chartapp.ui.ChartViewModel
import com.yusufbek.chartapp.ui.list.ListFragmentArgs

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    private var chart: ChartDataEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        chart = args.chart

        binding.tvChartTypeFragment.text = chart!!.type

        if (chart!!.type == "line" || chart!!.type == "bar") {
            binding.tvPieValueFragment.visibility = View.GONE
            "XValue: ${chart!!.xValue.toString()}".also { binding.tvXValueFragment.text = it }
            "YValue: ${chart!!.yValue.toString()}".also { binding.tvYValueFragment.text = it }
        } else {
            binding.tvXValueFragment.visibility = View.GONE
            binding.tvYValueFragment.visibility = View.GONE
            "PieValue: ${chart!!.pieValue.toString()}".also { binding.tvPieValueFragment.text = it }
        }

        return binding.root
    }

}