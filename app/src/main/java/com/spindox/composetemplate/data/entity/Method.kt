package com.spindox.composetemplate.data.entity

import androidx.room.Embedded
import com.squareup.moshi.Json

data class Method(
    @Json(name = "mash_temp")
    val mashTemp: List<MashTemp>?,
    @Embedded
    val fermentation: Fermentation?,
    val twist: String?
)