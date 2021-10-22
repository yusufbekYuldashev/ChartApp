package com.yusufbek.chartapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yusufbek.chartapp.R
import com.yusufbek.chartapp.adapters.ChartDataAdapter
import com.yusufbek.chartapp.databinding.FragmentListBinding
import com.yusufbek.chartapp.ui.ChartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val args: ListFragmentArgs by navArgs()

    private val viewModel: ChartViewModel by viewModels()

    private val chartDataAdapter: ChartDataAdapter = ChartDataAdapter()
    private var chartType: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        chartType = args.chartType

        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.rvFragmentList.apply {
            adapter = chartDataAdapter
        }

        lifecycleScope.launch {
            viewModel.getAllChartData(chartType).collectLatest {
                chartDataAdapter.submitData(it)
            }
        }

        chartDataAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("chart", it)
            }
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

        return binding.root
    }

}