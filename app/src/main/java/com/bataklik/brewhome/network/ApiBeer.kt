package com.bataklik.brewhome.network

import com.bataklik.brewhome.model.Beer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import timber.log.Timber


/**
 * Een vereenvoudigde weergave van een bier opgehaald van de API.
 * @property id De unieke identifier voor dit bier.
 * @property name De naam van dit bier.
 * @property tagline De tagline geassocieerd met dit bier.
 * @property firstBrewed De datum waarop dit bier voor het eerst is gebrouwen.
 * @property imageUrl De URL van de afbeelding die dit bier voorstelt.
 * @property abv Het alcoholpercentage van dit bier.
 * @constructor Creëert een [ApiBeer] met de gespecificeerde eigenschappen.
 */
@Serializable
data class ApiBeer(
    val id: Int,
    val name: String,
    val tagline: String,
    @SerialName("first_brewed") val firstBrewed: String,
    @SerialName("image_url") val imageUrl: String,
    val abv: Double,
)

/**
 * Converteert een [ApiBeer] object naar een [Beer] object.
 * @return Het geconverteerde [Beer] object.
 */
fun ApiBeer
    .asBeerObject() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    firstBrewed = firstBrewed,
    imageUrl = imageUrl,
    abv = abv
)


/**
 * Converteert een lijst met [ApiBeer] objecten naar een lijst met [Beer] objecten.
 * @return De lijst met geconverteerde [Beer] objecten.
 */
fun List<ApiBeer>.asBeerObjects(): List<Beer> {
    Timber.i("Timber start")
    return map {
        Timber.i("Timber: $it")
        Beer(
            id = it.id,
            name = it.name ?: "",
            tagline = it.tagline ?: "",
            abv = it.abv,
            imageUrl = it.imageUrl ?: "",
            firstBrewed = it.firstBrewed ?: "",
        )
    }
}
