package com.yusufbek.chartapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yusufbek.chartapp.databinding.ItemRvListFragmentBinding
import com.yusufbek.chartapp.localdb.ChartDataEntity

class ChartDataAdapter : PagingDataAdapter<ChartDataEntity, ChartDataAdapter.ChartViewHolder>(diffCallback = diffUtilCallback){

    private lateinit var binding: ItemRvListFragmentBinding

    companion object{
        val diffUtilCallback = object : DiffUtil.ItemCallback<ChartDataEntity>() {
            override fun areItemsTheSame(
                oldItem: ChartDataEntity,
                newItem: ChartDataEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ChartDataEntity,
                newItem: ChartDataEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        binding =
            ItemRvListFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val chartData = getItem(position)
        holder.binding.tvChartType.text = chartData!!.type
        if (chartData.type == "line" || chartData.type == "bar") {
            holder.binding.tvPieValue.visibility = View.GONE
            "xValue: ${chartData.xValue.toString()}".also { holder.binding.tvXValue.text = it }
            "yValue: ${chartData.yValue.toString()}".also { holder.binding.tvYValue.text = it }
        } else if (chartData.type == "pie") {
            holder.binding.tvXValue.visibility = View.GONE
            holder.binding.tvYValue.visibility = View.GONE
            "pieValue: ${chartData.pieValue.toString()}".also {
                holder.binding.tvPieValue.text = it
            }
        }
        holder.binding.clContainer.setOnClickListener {
            onItemClickListener?.let {
                it(getItem(position)!!)
            }
        }
    }

    inner class ChartViewHolder(val binding: ItemRvListFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((ChartDataEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (ChartDataEntity) -> Unit) {
        onItemClickListener = listener
    }
}