package com.giftech.frooit.core.utils

import com.giftech.frooit.core.data.source.remote.response.FruitResponse
import com.giftech.frooit.core.domain.model.Fruit

object DataMapper {

    fun mapResponseToDomain(input:List<FruitResponse>) =
        input.map {
            Fruit(
                id = it.id!!,
                name = it.name.toString(),
                family = it.family.toString(),
                genus = it.genus.toString()
            )
        }

}