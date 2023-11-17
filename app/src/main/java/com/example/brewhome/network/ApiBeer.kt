package com.example.brewhome.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ApiBeer(
    val id: Int?,
    val name: String,
    val tagline: String,
    val abv: Double,
    @SerializedName(value = "image_url")
    val imageUrl: String,
    @SerializedName(value = "first_brewed")
    val firstBrewed: String
)


public fun List<ApiBeer>.asDomainObjects(): List<Beer> {
    return map {
        Beer(
            id = it.id,
            name = it.name,
            tagline = it.tagline,
            abv = it.abv,
            imageUrl = it.imageUrl,
            firstBrewed = it.firstBrewed
        )
    }
}
