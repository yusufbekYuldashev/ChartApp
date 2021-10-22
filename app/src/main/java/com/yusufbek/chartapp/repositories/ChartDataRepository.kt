package com.yusufbek.chartapp.repositories

import androidx.paging.PagingSource
import com.yusufbek.chartapp.localdb.ChartDataDao
import com.yusufbek.chartapp.localdb.ChartDataEntity
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class ChartDataRepository @Inject constructor(
    private val dao: ChartDataDao
) {
    fun getChartData(chartType: String): Flow<List<ChartDataEntity>> {
        return dao.getChartDataByType(chartType)
    }

    fun getChartDataPaging(chartType: String): PagingSource<Int, ChartDataEntity> {
        return dao.getChartDataPaging(chartType)
    }
}