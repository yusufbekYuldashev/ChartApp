package com.yusufbek.chartapp.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "chart_data")
data class ChartDataEntity(
    var type: String = "line",
    var xValue: Float? = 0f,
    var yValue: Float? = 0f,
    var pieValue: Float? = 0f
):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}