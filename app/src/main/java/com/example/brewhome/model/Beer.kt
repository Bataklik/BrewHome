package com.example.brewhome.model

import com.example.brewhome.data.database.DbFavoriteBeer

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val imageUrl: String,
    val abv: Double,
)

fun Beer.asDbFavoriteBeer(): DbFavoriteBeer = DbFavoriteBeer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)

