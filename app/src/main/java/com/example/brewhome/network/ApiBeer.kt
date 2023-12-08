package com.example.brewhome.network

import com.example.brewhome.model.Beer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import timber.log.Timber


@Serializable
data class ApiBeer(
    val id: Int,
    val name: String,
    val tagline: String,
    @SerialName("first_brewed") val firstBrewed: String,
    @SerialName("image_url") val imageUrl: String,
    val abv: Double,
)

fun ApiBeer.asBeerObject() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    firstBrewed = firstBrewed,
    imageUrl = imageUrl,
    abv = abv
)


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
