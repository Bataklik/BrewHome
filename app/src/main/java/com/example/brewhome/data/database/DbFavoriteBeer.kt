package com.example.brewhome.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.brewhome.model.Beer
import com.example.brewhome.model.BeerDetail


@Entity
class DbFavoriteBeer(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "first_brewed") val firstBrewed: String,
    @ColumnInfo(name = "abv") val abv: Double
)

fun BeerDetail.asDbFavoriteBeer(): DbFavoriteBeer = DbFavoriteBeer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)

fun DbFavoriteBeer.asDomainBeer() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)


fun List<DbFavoriteBeer>
        .asDomainFavoriteBeers() = map { it.asDomainBeer() }