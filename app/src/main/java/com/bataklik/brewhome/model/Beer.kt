package com.bataklik.brewhome.model

import com.bataklik.brewhome.data.database.DbFavoriteBeer

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
) {
    /**
     * Hascode functie voor het [Beer] object, om het object te kunnen vergelijken met andere objecten.
     * @return Een hashcode van het [Beer] object.
     * */
    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + tagline.hashCode()
        result = 31 * result + firstBrewed.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + abv.hashCode()
        return result
    }

    /**
     * Equals functie voor het [Beer] object, om het object te kunnen vergelijken met andere objecten.
     * @return true als het [Beer] object gelijk is aan het andere object, anders false.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val otherBeer = other as Beer

        return id == otherBeer.id &&
                name == otherBeer.name &&
                tagline == otherBeer.tagline &&
                firstBrewed == otherBeer.firstBrewed &&
                imageUrl == otherBeer.imageUrl &&
                abv == otherBeer.abv
    }
}

/**
 * Extensiefunctie om een [Beer] object om te zetten naar een [DbFavoriteBeer] object.
 * @return [DbFavoriteBeer] object met dezelfde eigenschappen als het oorspronkelijke [Beer] object.
 */
fun Beer.asDbFavoriteBeer(): DbFavoriteBeer = DbFavoriteBeer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)

