package com.giftech.frooit.favourites

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouritesModule = module {
    viewModel { FavouritesViewModel(get()) }
}