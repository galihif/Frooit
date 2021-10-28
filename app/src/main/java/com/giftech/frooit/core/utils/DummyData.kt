package com.giftech.frooit.core.utils

import com.giftech.frooit.domain.model.Fruit

object DummyData {

    fun generateListFruit():List<Fruit>{
        val listFruit = ArrayList<Fruit>()
        for (i in 0..10){
            listFruit.add(
                Fruit(
                    i,
                    "Buah $i",
                    "Family $i",
                    "Genus $i",
                )
            )
        }
        return listFruit
    }

}