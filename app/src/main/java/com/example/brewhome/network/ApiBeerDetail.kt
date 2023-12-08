package com.example.brewhome.network


import com.example.brewhome.model.BeerDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import timber.log.Timber


@Serializable
data class ApiBeerDetail(
    val id: Int,
    val name: String?,
    val tagline: String?,
    @SerialName("first_brewed") val firstBrewed: String?,
    val description: String?,
    @SerialName("image_url") val imageUrl: String?,
    val abv: Double?,
    @Transient val ibu: Double? = null,
    @SerialName("target_fg") val targetFg: Int?,
    @SerialName("target_og") val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    @Transient val attenuationLevel: Double? = null,
    @Transient @SerialName("food_pairing") val foodPairing: List<String>? = null,
    @Transient @SerialName("brewers_tips") val brewersTips: String? = null,
    @Transient @SerialName("contributed_by") val contributedBy: String? = ""
)


fun ApiBeerDetail.asBeerObject(): BeerDetail {
    Timber.i("Timber start")
    return BeerDetail(
        id = this.id,
        name = this.name ?: "",
        tagline = this.tagline ?: "",
        abv = this.abv ?: 0.0,
        imageUrl = this.imageUrl ?: "",
        firstBrewed = this.firstBrewed ?: "",
        description = this.description ?: "",
        ibu = this.ibu ?: 0.0,
        attenuationLevel = this.attenuationLevel ?: 0.0,
        targetFg = this.targetFg ?: 0,
        targetOg = this.targetOg ?: 0.0,
        ebc = this.ebc ?: 0.0,
        srm = this.srm ?: 0.0,
        ph = this.ph ?: 0.0,
        foodPairing = this.foodPairing ?: emptyList(),
        brewersTips = this.brewersTips ?: "",
        contributedBy = this.contributedBy ?: ""
    )
}

