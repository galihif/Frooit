package com.giftech.frooit.di

import com.giftech.frooit.core.domain.usecase.FruitInteractor
import com.giftech.frooit.core.domain.usecase.FruitUseCase
import com.giftech.frooit.ui.detail.DetailViewModel
import com.giftech.frooit.ui.favourites.FavouritesViewModel
import com.giftech.frooit.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FruitUseCase> { FruitInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavouritesViewModel(get()) }
}