package com.areyana.charger.di

import com.areyana.charger.view.charges.ChargesProcessor
import com.areyana.charger.view.cities.CitiesProcessor
import org.koin.androidx.viewmodel.dsl.viewModelOf
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

    }
}

val dataModule by lazy {
    module {

    }
}