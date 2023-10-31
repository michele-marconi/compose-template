package com.spindox.composetemplate.data.entity

import androidx.room.Embedded

data class Hop(
    val name: String?,
    @Embedded(prefix = "hop_")
    val amount: BoilVolume?,
    val add: String?,
    val attribute: String?
)