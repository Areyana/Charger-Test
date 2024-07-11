package com.areyana.charger.di

import com.areyana.charger.data.ChargeCitiesRepository
import com.areyana.charger.data.ChargeCitiesRepositoryImpl
import com.areyana.charger.domain.ChargeCitiesInteractor
import com.areyana.charger.domain.ChargeCitiesInteractorImpl
import com.areyana.charger.view.charges.ChargesProcessor
import com.areyana.charger.view.cities.CitiesProcessor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule by lazy {
    module {
        includes(
            dataModule,
            interactorModule,
            viewModelModule,
        )
    }
}

val viewModelModule by lazy {
    module {
        viewModelOf(::ChargesProcessor)
        viewModelOf(::CitiesProcessor)
    }
}

val interactorModule by lazy {
    module {
        singleOf(::ChargeCitiesInteractorImpl) {
            bind<ChargeCitiesInteractor>()
        }
    }
}

val dataModule by lazy {
    module {
        singleOf(::ChargeCitiesRepositoryImpl) {
            bind<ChargeCitiesRepository>()
        }
    }
}