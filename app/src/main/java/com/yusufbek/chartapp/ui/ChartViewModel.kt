package com.yusufbek.chartapp.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yusufbek.chartapp.localdb.ChartDataEntity
import com.yusufbek.chartapp.repositories.ChartDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val repository: ChartDataRepository
) : ViewModel() {

    fun getAllChartData(type:String):Flow<PagingData<ChartDataEntity>>{
        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 30)
        ){
            repository.getChartDataPaging(type)
        }.flow
    }

//    private val _chartData = MutableLiveData<List<ChartDataEntity>>()
//    val chartData: MutableLiveData<List<ChartDataEntity>> = MutableLiveData<List<ChartDataEntity>>()

//    init {
//        getChartDataByType("line")
//    }

//    fun getChartDataByType(chartType: String) {
//        viewModelScope.launch {
//            repository.getChartData(chartType).collect { data ->
//                chartData.postValue(data)
//                Log.d("TAGGED", "$chartType ${data.size} in viewModel")
//            }
//        }
//    }
    fun getChartDataByType(chartType: String):LiveData<List<ChartDataEntity>> = repository.getChartData(chartType).asLiveData()
}