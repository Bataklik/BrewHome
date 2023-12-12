package com.example.brewhome.model

import com.example.brewhome.data.database.DbFavoriteBeer
/**
 * Dataklasse die een bierobject vertegenwoordigt.
 * @property id Unieke identificatie voor het bier.
 * @property name Naam van het bier.
 * @property tagline Korte tagline of beschrijving van het bier.
 * @property firstBrewed Datum waarop het bier voor het eerst werd gebrouwen.
 * @property imageUrl URL naar de afbeelding van het bier.
 * @property abv Alcoholpercentage van het bier op volume (ABV).
 */
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val imageUrl: String,
    val abv: Double,
)

/**
 * Extensiefunctie om een [Beer] object om te zetten naar een [DbFavoriteBeer] object.
 * @return [DbFavoriteBeer] object met dezelfde eigenschappen als het oorspronkelijke [Beer] object.
 */
fun Beer
    .asDbFavoriteBeer(): DbFavoriteBeer = DbFavoriteBeer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)

