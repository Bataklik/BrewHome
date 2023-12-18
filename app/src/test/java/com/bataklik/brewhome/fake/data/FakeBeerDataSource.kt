package com.bataklik.brewhome.fake.data

import com.bataklik.brewhome.network.ApiBeer
import com.bataklik.brewhome.network.ApiBeerDetail

object FakeBeerDataSource {
    private const val BEER_ID_ONE = 1
    private const val BEER_NAME_ONE = "Stella Artois"
    private const val BEER_TAGLINE_ONE = "Samen genieten van het goed leven, dat is THE LIFE * ARTOIS"
    private const val BEER_IMAGE_ONE =
        "https://www.prikentik.be/media/catalog/product/s/t/stella_bottle_25cl.png"
    private const val BEER_FIRST_BREW_ONE = "1366"
    private const val BEER_ABV_ONE = 5.2

    private const val BEER_ID_TWO = 2
    private const val BEER_NAME_TWO = "Tout Bien"
    private const val BEER_TAGLINE_TWO = "An average Belgian Pils. Nothing more, nothing less."
    private const val BEER_IMAGE_TWO = "https://goedkoopdrank.be/wp-content/uploads/2023/04/GD54014.jpg"
    private const val BEER_FIRST_BREW_TWO = "2021-04-28"
    private const val BEER_ABV_TWO = 5.2

    val BEERS = listOf(
        ApiBeer(
            id = BEER_ID_ONE,
            name = BEER_NAME_ONE,
            tagline = BEER_TAGLINE_ONE,
            imageUrl = BEER_IMAGE_ONE,
            firstBrewed = BEER_FIRST_BREW_ONE,
            abv = BEER_ABV_ONE
        ),
        ApiBeer(
            id = BEER_ID_TWO,
            name = BEER_NAME_TWO,
            tagline = BEER_TAGLINE_TWO,
            imageUrl = BEER_IMAGE_TWO,
            firstBrewed = BEER_FIRST_BREW_TWO,
            abv = BEER_ABV_TWO
        )
    )

    val BEER_DETAIL = ApiBeerDetail(
        id = BEER_ID_ONE,
        name = BEER_NAME_ONE,
        tagline = BEER_TAGLINE_ONE,
        imageUrl = BEER_IMAGE_ONE,
        firstBrewed = BEER_FIRST_BREW_ONE,
        abv = BEER_ABV_ONE,
        description = "Stella Artois nodigt iedereen uit om samen van het leven te genieten. Een unieke smaak, perfect om van te genieten, om te delen en om een toast uit te brengen op het goede leven. The Life Artois viert de Belgische levensstijl die volledig draait om genieten van de kleine dingen, samen met onze geliefden! Serveer en geniet van de meer dan 600 jaar rijke Belgische brouwtraditie van Stella Artois.",
        contributedBy = "Artois familie",
        attenuationLevel = 2.0,
        brewersTips = "Malt",
        ph = 4.0,
        ebc = 6.0,
        foodPairing = listOf("Cheese"),
        ibu = 24.0,
        srm = 3.0,
        targetFg = 9,
        targetOg = 2.9
    )
}
