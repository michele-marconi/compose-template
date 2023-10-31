package com.spindox.composetemplate.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Beers")
data class Beer(
    @PrimaryKey val id: Long,
    val name: String?,
    val tagline: String?,
    @Json(name = "first_brewed")
    val firstBrewed: String?,
    val description: String?,
    @Json(name = "image_url")
    val imageURL: String?,
    val abv: Double?,
    val ibu: Double?,
    @Json(name = "target_fg")
    val targetFg: Double?,
    @Json(name = "target_og")
    val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    @Json(name = "attenuation_level")
    val attenuationLevel: Double?,
    @Embedded(prefix = "volume_")
    val volume: BoilVolume,
    @Embedded(prefix = "boilVolume_")
    @Json(name = "boil_volume")
    val boilVolume: BoilVolume?,
    /*@Embedded
    val method: Method?,
    @Embedded
    val ingredients: Ingredients?,
    @Json(name = "food_pairing")
    val foodPairingsList: List<String>?,*/
    @Json(name = "brewers_tips")
    val brewersTips: String?,
    @Json(name = "contributed_by")
    val contributedBy: String?
)

