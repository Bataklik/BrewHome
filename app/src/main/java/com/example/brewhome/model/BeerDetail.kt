package com.example.brewhome.model

import com.example.brewhome.data.database.DbFavoriteBeer

data class BeerDetail(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
    val abv: Double,
    val ibu: Double?,
    val attenuationLevel: Double?,
    val targetFg: Int?,
    val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
)

fun BeerDetail.asBeer(): Beer = Beer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)