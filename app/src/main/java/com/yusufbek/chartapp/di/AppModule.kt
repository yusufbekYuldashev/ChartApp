package com.yusufbek.chartapp.di

import android.content.Context
import androidx.room.Room
import com.yusufbek.chartapp.localdb.ChartDataDao
import com.yusufbek.chartapp.localdb.ChartDataDatabase
import com.yusufbek.chartapp.repositories.ChartDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsDao(@ApplicationContext app: Context) =
        ChartDataDatabase.get(app).getChartDataDao()
}