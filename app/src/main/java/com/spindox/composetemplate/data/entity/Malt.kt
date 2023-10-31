package com.spindox.composetemplate.data.entity

import androidx.room.Embedded

data class Malt(
    val name: String?,
    @Embedded(prefix = "malt_")
    val amount: BoilVolume?
)