package com.spindox.composetemplate.data.entity

import androidx.room.Embedded

data class MashTemp(
    @Embedded(prefix = "mash_temp_")
    val temp: BoilVolume?,
    val duration: Int?
)