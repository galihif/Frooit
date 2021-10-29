package com.giftech.frooit.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    var id:Int = 0,
    var name:String = "",
    var family:String = "",
    var genus:String = ""
):Parcelable