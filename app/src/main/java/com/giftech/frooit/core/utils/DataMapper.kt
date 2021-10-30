package com.giftech.frooit.core.utils

import com.giftech.frooit.core.data.source.local.entity.FruitEntity
import com.giftech.frooit.core.data.source.remote.response.FruitResponse
import com.giftech.frooit.core.domain.model.Fruit

object DataMapper {

    fun mapResponseToEntity(input:List<FruitResponse>) =
        input.map {
            FruitEntity(
                id = it.id!!,
                name = it.name.toString(),
                family = it.family.toString(),
                genus = it.genus.toString()
            )
        }

    fun mapEntitiesToDomain(input: List<FruitEntity>) =
        input.map {
            Fruit(
                id = it.id,
                name = it.name,
                family = it.family,
                genus = it.genus,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Fruit) =
        FruitEntity(
            id = input.id,
            name = input.name,
            family = input.family,
            genus = input.genus,
            isFavorite = input.isFavorite
        )

}