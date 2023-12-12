package com.bataklik.brewhome.network


import com.bataklik.brewhome.model.BeerDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import timber.log.Timber

/**
 * Een vereenvoudigde weergave van een bier opgehaald van de API.
 *
 * @property id De unieke identificatie voor dit bier.
 * @property name De naam van dit bier.
 * @property tagline De tagline geassocieerd met dit bier.
 * @property firstBrewed De datum waarop dit bier voor het eerst is gebrouwen.
 * @property description De beschrijving van dit bier.
 * @property imageUrl De URL van de afbeelding die dit bier voorstelt.
 * @property abv Het alcoholpercentage van dit bier.
 * @property ibu De International Bitterness Units (IBU) van dit bier.
 * @property targetFg Het doel eind-dichtheid van dit bier.
 * @property targetOg Het doel begin-dichtheid van dit bier.
 * @property ebc De European Brewery Convention (EBC) kleur van dit bier.
 * @property srm De Standard Reference Method (SRM) kleur van dit bier.
 * @property ph De zuurgraad (pH) van dit bier.
 * @property attenuationLevel Het attenuatieniveau van dit bier.
 * @property foodPairing Een lijst van voedselcombinaties die goed samengaan met dit bier.
 * @property brewersTips Tips van de brouwer voor dit bier.
 * @property contributedBy Bijdrage van de gebruiker aan dit bier.
 * @constructor Creëert een [ApiBeerDetail] met de gespecificeerde eigenschappen.
 */
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

/**
 * Converteert een [ApiBeerDetail] object naar een [BeerDetail] object.
 * @return Het geconverteerde [BeerDetail] object.
 */
fun ApiBeerDetail
    .asBeerObject(): BeerDetail {
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

