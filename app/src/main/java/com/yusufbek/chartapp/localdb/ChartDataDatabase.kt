package com.yusufbek.chartapp.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [ChartDataEntity::class],
    version = 1
)
abstract class ChartDataDatabase : RoomDatabase() {

    abstract fun getChartDataDao(): ChartDataDao

    companion object {
        private var instance: ChartDataDatabase? = null

        @Synchronized
        fun get(context: Context): ChartDataDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChartDataDatabase::class.java, "ChartDatabase"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        fillInDb(context.applicationContext)
                    }
                }).build()
            }
            return instance!!
        }

        /**
         * fill database with list of cheeses
         */
        private fun fillInDb(context: Context) {
            // inserts in Room are executed on the current thread, so we insert in the background
            GlobalScope.launch {
                get(context).getChartDataDao().insertChartData(
                    CHART_DATA
                )
            }
        }
    }
}

private val CHART_DATA = arrayListOf(
    ChartDataEntity("line", 5f, 8f),
    ChartDataEntity("line", 5.6f, 8.2f),
    ChartDataEntity("line", 5.8f, 8.9f),
    ChartDataEntity("line", 6.1f, 9.3f),
    ChartDataEntity("line", 6.5f, 9.8f),
    ChartDataEntity("line", 7f, 11f),
    ChartDataEntity("line", 8.5f, 12.5f),
    ChartDataEntity("line", 9f, 13.6f),
    ChartDataEntity("line", 11f, 15f),
    ChartDataEntity("line", 12f, 18f),
    ChartDataEntity("bar", 4.3f, 5.3f),
    ChartDataEntity("bar", 4.5f, 7f),
    ChartDataEntity("bar", 8f, 9f),
    ChartDataEntity("bar", 15f, 16f),
    ChartDataEntity("bar", 13f, 7.9f),
    ChartDataEntity("bar", 15.6f, 22f),
    ChartDataEntity("bar", 18f, 26f),
    ChartDataEntity("bar", 22f, 35f),
    ChartDataEntity("bar", 25f, 36.3f),
    ChartDataEntity("bar", 27f, 45.3f),
    ChartDataEntity("bar", 3.6f, 6.6f),
    ChartDataEntity("bar", 5.4f, 8.5f),
    ChartDataEntity("bar", 8.85f, 9.99f),
    ChartDataEntity("bar", 15.3f, 18f),
    ChartDataEntity("bar", 13.5f, 9.7f),
    ChartDataEntity("pie", 0f, 0f, 50f),
    ChartDataEntity("pie", 0f, 0f, 103f),
    ChartDataEntity("pie", 0f, 0f, 59f),
    ChartDataEntity("pie", 0f, 0f, 61f),
    ChartDataEntity("pie", 0f, 0f, 65f),
    ChartDataEntity("pie", 0f, 0f, 68f),
    ChartDataEntity("pie", 0f, 0f, 89f),
    ChartDataEntity("pie", 0f, 0f, 83f),
    ChartDataEntity("pie", 0f, 0f, 86f),
    ChartDataEntity("pie", 0f, 0f, 95f),

    ChartDataEntity("line", 15f, 18f),
    ChartDataEntity("line", 15.6f, 18.2f),
    ChartDataEntity("line", 19.8f, 18.9f),
    ChartDataEntity("line", 16.1f, 19.3f),
    ChartDataEntity("line", 6.5f, 19.8f),
    ChartDataEntity("line", 17f, 31f),
    ChartDataEntity("line", 18.5f, 42.5f),
    ChartDataEntity("line", 29f, 53.6f),
    ChartDataEntity("line", 31f, 25f),
    ChartDataEntity("line", 42f, 38f),
    ChartDataEntity("line", 36f, 66f),
    ChartDataEntity("line", 54f, 85f),
    ChartDataEntity("line", 88.5f, 99.9f),
    ChartDataEntity("line", 35.3f, 48f),
    ChartDataEntity("line", 73.5f, 97f),
    ChartDataEntity("bar", 14.3f, 15.3f),
    ChartDataEntity("bar", 14.5f, 17f),
    ChartDataEntity("bar", 18f, 19f),
    ChartDataEntity("bar", 25f, 26f),
    ChartDataEntity("bar", 23f, 17.9f),
    ChartDataEntity("bar", 25.6f, 32f),
    ChartDataEntity("bar", 28f, 36f),
    ChartDataEntity("bar", 32f, 45f),
    ChartDataEntity("bar", 35f, 46.3f),
    ChartDataEntity("bar", 37f, 55.3f),
    ChartDataEntity("pie", 0f, 0f, 57f),
    ChartDataEntity("pie", 0f, 0f, 133f),
    ChartDataEntity("pie", 0f, 0f, 79f),
    ChartDataEntity("pie", 0f, 0f, 81f),
    ChartDataEntity("pie", 0f, 0f, 45f),
    ChartDataEntity("pie", 0f, 0f, 58f),
    ChartDataEntity("pie", 0f, 0f, 29f),
    ChartDataEntity("pie", 0f, 0f, 73f),
    ChartDataEntity("pie", 0f, 0f, 56f),
    ChartDataEntity("pie", 0f, 0f, 45f),

    ChartDataEntity("line", 25f, 28f),
    ChartDataEntity("line", 25.6f, 28.2f),
    ChartDataEntity("line", 25.8f, 28.9f),
    ChartDataEntity("line", 26.1f, 29.3f),
    ChartDataEntity("line", 26.5f, 29.8f),
    ChartDataEntity("line", 27f, 31f),
    ChartDataEntity("line", 28.5f, 32.5f),
    ChartDataEntity("line", 29f, 33.6f),
    ChartDataEntity("line", 31f, 35f),
    ChartDataEntity("line", 32f, 38f),
    ChartDataEntity("bar", 24.3f, 25.3f),
    ChartDataEntity("bar", 24.5f, 27f),
    ChartDataEntity("bar", 28f, 29f),
    ChartDataEntity("bar", 35f, 36f),
    ChartDataEntity("bar", 33f, 27.9f),
    ChartDataEntity("bar", 35.6f, 42f),
    ChartDataEntity("bar", 38f, 46f),
    ChartDataEntity("bar", 42f, 55f),
    ChartDataEntity("bar", 45f, 56.3f),
    ChartDataEntity("bar", 47f, 65.3f),
    ChartDataEntity("pie", 0f, 0f, 70f),
    ChartDataEntity("pie", 0f, 0f, 123f),
    ChartDataEntity("pie", 0f, 0f, 79f),
    ChartDataEntity("pie", 0f, 0f, 81f),
    ChartDataEntity("pie", 0f, 0f, 85f),
    ChartDataEntity("pie", 0f, 0f, 88f),
    ChartDataEntity("pie", 0f, 0f, 109f),
    ChartDataEntity("pie", 0f, 0f, 103f),
    ChartDataEntity("pie", 0f, 0f, 106f),
    ChartDataEntity("pie", 0f, 0f, 115f),
    ChartDataEntity("pie", 0f, 0f, 76f),
    ChartDataEntity("pie", 0f, 0f, 35f),
    ChartDataEntity("pie", 0f, 0f, 49f),
    ChartDataEntity("pie", 0f, 0f, 28f),
    ChartDataEntity("pie", 0f, 0f, 73f),
)