package com.areyana.charger.data

import android.content.Context
import com.areyana.charger.R
import com.areyana.charger.data.model.ChargeCityDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ChargeCitiesRepositoryImpl(private val context: Context): ChargeCitiesRepository {

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
    override suspend fun loadChargeCities(): Result<List<ChargeCityDto>> = withContext(Dispatchers.IO) {
        delay(1000)
        runCatching {
            val testText = context.resources.openRawResource(R.raw.test).bufferedReader().use { it.readText() }
            json.decodeFromString(testText)
        }
    }
}