package com.bataklik.brewhome.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail


/**
 * Entity-klasse die de representatie van een favoriet bier in de lokale Room-database definieert.
 * @param id Het unieke ID van het favoriete bier.
 * @param name De naam van het favoriete bier.
 * @param tagline De tagline van het favoriete bier.
 * @param imageUrl De URL van de afbeelding van het favoriete bier.
 * @param firstBrewed De datum waarop het favoriete bier voor het eerst werd gebrouwen.
 * @param abv Het alcoholpercentage (ABV) van het favoriete bier.
 */
@Entity
class DbFavoriteBeer(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "first_brewed") val firstBrewed: String,
    @ColumnInfo(name = "abv") val abv: Double
)
/**
 * Extension-functie die een [BeerDetail]-object omzet in een [DbFavoriteBeer]-object voor opslag in de lokale database.
 * @return Een [DbFavoriteBeer]-object dat is afgeleid van het [BeerDetail]-object.
 */
fun BeerDetail
    .asDbFavoriteBeer(): DbFavoriteBeer = DbFavoriteBeer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)
/**
 * Extension-functie die een [DbFavoriteBeer]-object omzet in een [Beer]-object voor gebruik in de domeinlaag.
 * @return Een [Beer]-object dat is afgeleid van het [DbFavoriteBeer]-object.
 */
fun DbFavoriteBeer
    .asDomainBeer() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)

/**
 * Extension-functie die een lijst van [DbFavoriteBeer]-objecten omzet in een lijst van [Beer]-objecten voor gebruik in de domeinlaag.
 * @return Een lijst van [Beer]-objecten die zijn afgeleid van de lijst van [DbFavoriteBeer]-objecten.
 */
fun List<DbFavoriteBeer>
        .asDomainFavoriteBeers() = map { it.asDomainBeer() }