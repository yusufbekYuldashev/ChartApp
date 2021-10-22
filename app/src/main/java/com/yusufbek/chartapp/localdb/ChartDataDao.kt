package com.yusufbek.chartapp.localdb

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChartDataDao {

    @Query("SELECT * from chart_data where type = :chartType LIMIT 10")
    fun getChartDataByType(chartType: String): Flow<List<ChartDataEntity>>

    @Query("SELECT * from chart_data where type = :chartType")
    fun getChartDataPaging(chartType: String): PagingSource<Int, ChartDataEntity>

    @Insert
    fun insertChartData(chartDataL: List<ChartDataEntity>)

}