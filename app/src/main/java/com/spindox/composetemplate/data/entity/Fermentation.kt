package com.spindox.composetemplate.data.entity

import androidx.room.Embedded

data class Fermentation(
    @Embedded(prefix = "fermentation_")
    val temp: BoilVolume?
)